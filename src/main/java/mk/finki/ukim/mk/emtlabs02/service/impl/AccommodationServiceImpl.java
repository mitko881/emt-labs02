package mk.finki.ukim.mk.emtlabs02.service.impl;
import mk.finki.ukim.mk.emtlabs02.dto.PopularAccommodationDto;
import mk.finki.ukim.mk.emtlabs02.dto.PopularHostDto;
import mk.finki.ukim.mk.emtlabs02.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationFilterDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.event.AccommodationRentedEvent;
import mk.finki.ukim.mk.emtlabs02.exception.BadRequestException;
import mk.finki.ukim.mk.emtlabs02.exception.ResourceNotFoundException;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.model.Category;
import mk.finki.ukim.mk.emtlabs02.model.Host;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationDetailsView;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationShortView;
import mk.finki.ukim.mk.emtlabs02.repository.AccommodationRepository;
import mk.finki.ukim.mk.emtlabs02.repository.HostRepository;
import mk.finki.ukim.mk.emtlabs02.service.AccommodationService;
import mk.finki.ukim.mk.emtlabs02.specification.AccommodationSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ActivityLogRepository activityLogRepository;

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Object findById(Long id) {
        return accommodationRepository.findWithHostAndCountryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found"));
    }

    @Override
    public Accommodation create(AccommodationCreateDto dto) {
        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        Accommodation accommodation = Accommodation.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .condition(dto.getCondition())
                .host(host)
                .numRooms(dto.getNumRooms())
                .rented(dto.getNumRooms() == 0)
                .build();

        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation update(Long id, AccommodationUpdateDto dto) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found"));

        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        accommodation.setName(dto.getName());
        accommodation.setCategory(dto.getCategory());
        accommodation.setCondition(dto.getCondition());
        accommodation.setHost(host);
        accommodation.setNumRooms(dto.getNumRooms());
        accommodation.setRented(dto.getNumRooms() == 0);

        return accommodationRepository.save(accommodation);
    }

    @Override
    public void delete(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found"));

        accommodationRepository.delete(accommodation);
    }

    @Override
    public Accommodation markAsRented(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found"));

        if (accommodation.getNumRooms() <= 0) {
            throw new BadRequestException("No free rooms available");
        }

        accommodation.setNumRooms(accommodation.getNumRooms() - 1);

        if (accommodation.getNumRooms() == 0) {
            accommodation.setRented(true);
        }

        Accommodation saved = accommodationRepository.save(accommodation);
        applicationEventPublisher.publishEvent(new AccommodationRentedEvent(this, saved));

        return saved;
    }

    @Override
    public Page<Accommodation> search(AccommodationFilterDto filterDto, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Accommodation> specification = Specification
                .where(AccommodationSpecification.hasCategory(filterDto.getCategory()))
                .and(AccommodationSpecification.hasHost(filterDto.getHostId()))
                .and(AccommodationSpecification.hasCountryName(filterDto.getCountryName()))
                .and(AccommodationSpecification.hasNumRooms(filterDto.getNumRooms()))
                .and(AccommodationSpecification.hasAvailable(filterDto.getAvailable()));

        return accommodationRepository.findAll(specification, pageable);
    }

    @Override
    public List<AccommodationShortView> findAllShortProjection() {
        return accommodationRepository.findAllProjectedBy();
    }

    @Override
    public List<PopularAccommodationDto> getMostPopularAccommodations() {
        return null;
    }

    @Override
    public List<PopularHostDto> getMostPopularHosts() {
        return null;
    }

    @Override
    public List<AccommodationDetailsView> findByCategoryProjection(String category) {
        return accommodationRepository.findByCategory(Category.valueOf(category.toUpperCase()));
    }

}