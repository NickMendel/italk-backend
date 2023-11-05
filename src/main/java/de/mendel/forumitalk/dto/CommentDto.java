package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Topic;
import de.mendel.forumitalk.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CommentDto {

    @NotBlank(message = "Comment body is required")
    private String body;
    private LocalDate updated_at = LocalDate.now();

    public CommentDto(String body) {
        this.body = body;
    }
}
