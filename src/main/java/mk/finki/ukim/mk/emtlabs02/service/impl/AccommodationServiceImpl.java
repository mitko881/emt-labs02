package mk.finki.ukim.mk.emtlabs02.service.impl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.exception.BadRequestException;
import mk.finki.ukim.mk.emtlabs02.exception.ResourceNotFoundException;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.model.Condition;
import mk.finki.ukim.mk.emtlabs02.model.Host;
import mk.finki.ukim.mk.emtlabs02.repository.AccommodationRepository;
import mk.finki.ukim.mk.emtlabs02.repository.HostRepository;
import mk.finki.ukim.mk.emtlabs02.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
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
                .rented(false)
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

        if (accommodation.getCondition() == Condition.BAD) {
            throw new BadRequestException("Accommodation with BAD condition cannot be rented");
        }

        accommodation.setRented(true);
        return accommodationRepository.save(accommodation);
    }
}