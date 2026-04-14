package mk.finki.ukim.mk.emtlabs02.controller;

import mk.finki.ukim.mk.emtlabs02.dto.AccommodationCreateDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationFilterDto;
import mk.finki.ukim.mk.emtlabs02.dto.AccommodationUpdateDto;
import mk.finki.ukim.mk.emtlabs02.model.Category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationDetailsView;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationShortView;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationStatsProjection;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationViewProjection;
import mk.finki.ukim.mk.emtlabs02.repository.ActivityLogRepository;
import mk.finki.ukim.mk.emtlabs02.repository.AccommodationStatsRepository;
import mk.finki.ukim.mk.emtlabs02.repository.AccommodationViewRepository;
import mk.finki.ukim.mk.emtlabs02.service.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationViewRepository accommodationViewRepository;
    private final AccommodationStatsRepository accommodationStatsRepository;
    private final ActivityLogRepository activityLogRepository;

    @GetMapping
    public ResponseEntity<List<Accommodation>> findAll() {
        return ResponseEntity.ok(accommodationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return ResponseEntity.ok((Accommodation) accommodationService.findById(id));
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

    @GetMapping("/search")
    public ResponseEntity<Page<Accommodation>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) mk.finki.ukim.mk.emtlabs02.model.Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) Integer numRooms,
            @RequestParam(required = false) Boolean available
    ) {
        AccommodationFilterDto filterDto = new AccommodationFilterDto();
        filterDto.setCategory(category);
        filterDto.setHostId(hostId);
        filterDto.setCountryName(countryName);
        filterDto.setNumRooms(numRooms);
        filterDto.setAvailable(available);

        return ResponseEntity.ok(accommodationService.search(filterDto, page, size, sortBy, direction));
    }

    @GetMapping("/projection/short")
    public ResponseEntity<List<AccommodationShortView>> getShortProjection() {
        return ResponseEntity.ok(accommodationService.findAllShortProjection());
    }

    @GetMapping("/projection/details")
    public ResponseEntity<List<AccommodationDetailsView>> getDetailsProjection(@RequestParam String category) {
        return ResponseEntity.ok(accommodationService.findByCategoryProjection(category));
    }

    @GetMapping("/view")
    public ResponseEntity<List<AccommodationViewProjection>> getViewData() {
        return ResponseEntity.ok(accommodationViewRepository.findAllFromView());
    }

    @GetMapping("/stats")
    public ResponseEntity<List<AccommodationStatsProjection>> getStats() {
        return ResponseEntity.ok(accommodationStatsRepository.getStats());
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(activityLogRepository.findAll(PageRequest.of(page, size)));
    }



}