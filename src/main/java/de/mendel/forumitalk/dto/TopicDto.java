package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Comment;
import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

    @NotBlank(message = "Title cannot be empty")
    @Max(value= 60, message = "Title cannot be longer than 60 characters")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    @Min(value = 10, message = "Description cannot be shorter than 10 characters")
    private String description;
}
