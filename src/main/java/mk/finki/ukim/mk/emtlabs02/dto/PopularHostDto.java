package mk.finki.ukim.mk.emtlabs02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularHostDto {
    private String hostFullName;
    private Long rentCount;
}