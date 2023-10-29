package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SectionDto {

    private Long id;
    @NotBlank
    @Max(30)
    private String name;
    @NotBlank
    private String description;

    public SectionDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
