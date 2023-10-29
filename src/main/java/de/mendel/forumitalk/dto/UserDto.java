package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Topic;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    @NotBlank
    @Max(30)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private List<Topic> topics;
    private String error;

    public UserDto(Long user_id, String username, String email, String password, List<Topic> topics) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.topics = topics;
    }
}
