package mk.finki.ukim.mk.emtlabs02.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.service.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<List<Accommodation>> findAll() {
        return ResponseEntity.ok(accommodationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Accommodation> create(@Valid @RequestBody AccommodationCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id,
                                                @Valid @RequestBody AccommodationUpdateDto dto) {
        return ResponseEntity.ok(accommodationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accommodationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.markAsRented(id));
    }
}