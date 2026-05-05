package mk.finki.ukim.mk.emtlabs02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularAccommodationDto {
    private String accommodationName;
    private Long rentCount;
}