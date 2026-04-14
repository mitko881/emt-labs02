package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.Accommodation;
import mk.finki.ukim.mk.emtlabs02.model.Category;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationDetailsView;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationShortView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<Accommodation> findWithHostAndCountryById(Long id);

    List<AccommodationShortView> findAllProjectedBy();

    List<AccommodationDetailsView> findByCategory(Category category);

    Page<Accommodation> findAll(Specification<Accommodation> specification, Pageable pageable);
}