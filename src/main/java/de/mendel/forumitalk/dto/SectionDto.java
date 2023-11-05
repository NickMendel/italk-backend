package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto {

    @NotBlank(message = "Title is mandatory")
    @Max(value = 45, message = "Title is too long")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;
}
