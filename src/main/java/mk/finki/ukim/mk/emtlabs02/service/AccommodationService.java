package mk.finki.ukim.mk.emtlabs02.service;

import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationFilterDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationDetailsView;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationShortView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findAll();
    Object findById(Long id);
    Accommodation create(AccommodationCreateDto dto);
    Accommodation update(Long id, AccommodationUpdateDto dto);
    void delete(Long id);
    Accommodation markAsRented(Long id);

    Page<Accommodation> search(AccommodationFilterDto filterDto, int page, int size, String sortBy, String direction);

    List<AccommodationShortView> findAllShortProjection();

    List<AccommodationDetailsView> findByCategoryProjection(String category);
}