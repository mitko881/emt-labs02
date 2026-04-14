package mk.finki.ukim.mk.emtlabs02.service;

import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findAll();
    Accommodation findById(Long id);
    Accommodation create(AccommodationCreateDto dto);
    Accommodation update(Long id, AccommodationUpdateDto dto);
    void delete(Long id);
    Accommodation markAsRented(Long id);
}