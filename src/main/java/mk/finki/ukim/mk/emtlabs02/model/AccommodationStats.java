package mk.finki.ukim.mk.emtlabs02.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "accommodation_stats_mv")
@Getter
@Setter
public class AccommodationStats {

    @Id
    @Column(name = "category")
    private String category;

    @Column(name = "total_accommodations")
    private Long totalAccommodations;

    @Column(name = "total_rooms")
    private Long totalRooms;

    @Column(name = "avg_rooms")
    private java.math.BigDecimal avgRooms;
}