package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.AccommodationViewEntity;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccommodationViewRepository extends JpaRepository<AccommodationViewEntity, Long> {

    @Query(value = "SELECT * FROM accommodation_view", nativeQuery = true)
    List<AccommodationViewProjection> findAllFromView();
}