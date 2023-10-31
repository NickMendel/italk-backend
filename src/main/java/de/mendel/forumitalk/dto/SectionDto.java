package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Topic;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SectionDto {

    private Long id;
    @NotBlank
    @Max(45)
    private String title;
    @NotBlank
    private String description;

    private List<Topic> topics;

    public SectionDto(Long id, String title, String description, List<Topic> topics) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topics = topics;
    }
}
