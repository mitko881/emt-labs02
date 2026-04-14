package mk.finki.ukim.mk.emtlabs02.dto;

import lombok.Data;
import mk.finki.ukim.mk.emtlabs02.model.Category;

@Data
public class AccommodationFilterDto {
    private Category category;
    private Long hostId;
    private String countryName;
    private Integer numRooms;
    private Boolean available;
}