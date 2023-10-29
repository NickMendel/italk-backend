package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicDto {

        private Long id;
        @NotBlank
        @Max(30)
        private String title;
        @NotBlank
        private String description;
        @NotBlank
        private Section section;
        @NotBlank
        private User author;

        public TopicDto(Long id, String title, String description, Section section, User author) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.section = section;
            this.author = author;
        }
}
