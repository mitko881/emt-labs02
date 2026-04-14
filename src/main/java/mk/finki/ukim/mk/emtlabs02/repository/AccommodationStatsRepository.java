package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.AccommodationStats;
import mk.finki.ukim.mk.emtlabs02.projection.AccommodationStatsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccommodationStatsRepository extends JpaRepository<AccommodationStats, String> {

    @Query(value = "SELECT * FROM accommodation_stats_mv", nativeQuery = true)
    List<AccommodationStatsProjection> getStats();
}