package mk.finki.ukim.mk.emtlabs02.projection;

import java.math.BigDecimal;

public interface AccommodationStatsProjection {
    String getCategory();
    Long getTotalAccommodations();
    Integer getTotalRooms();
    BigDecimal getAvgRooms();
}