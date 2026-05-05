package mk.finki.ukim.mk.emtlabs02.repository;

import mk.finki.ukim.mk.emtlabs02.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    @Query(value = """
            SELECT accommodation_name, COUNT(*) AS rent_count
            FROM activity_logs
            WHERE event_type IN ('RENTED', 'FULLY_BOOKED')
            GROUP BY accommodation_name
            ORDER BY rent_count DESC
            """, nativeQuery = true)
    List<Object[]> findMostPopularAccommodations();

    @Query(value = """
            SELECT CONCAT(h.name, ' ', h.surname) AS host_full_name, COUNT(al.id) AS rent_count
            FROM activity_logs al
            JOIN accommodations a ON a.name = al.accommodation_name
            JOIN hosts h ON a.host_id = h.id
            WHERE al.event_type IN ('RENTED', 'FULLY_BOOKED')
            GROUP BY h.name, h.surname
            ORDER BY rent_count DESC
            """, nativeQuery = true)
    List<Object[]> findMostPopularHosts();
}