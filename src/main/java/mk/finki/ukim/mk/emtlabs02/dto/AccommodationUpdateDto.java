package mk.finki.ukim.mk.emtlabs02.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mk.finki.ukim.mk.emtlabs02.model.Category;
import mk.finki.ukim.mk.emtlabs02.model.Condition;

@Data
public class AccommodationUpdateDto {

    @NotBlank
    private String name;

    @NotNull
    private Category category;

    @NotNull
    private Condition condition;

    @NotNull
    private Long hostId;

    @NotNull
    @Min(1)
    private Integer numRooms;
}