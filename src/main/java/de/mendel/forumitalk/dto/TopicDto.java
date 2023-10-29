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

        private Long topic_id;
        @NotBlank
        @Max(60)
        private String title;
        @NotBlank
        private String description;
        private Section section;
        private User user;

        public TopicDto(Long topic_id, String title, String description, Section section, User user) {
            this.topic_id = topic_id;
            this.title = title;
            this.description = description;
            this.section = section;
            this.user = user;
        }
}
