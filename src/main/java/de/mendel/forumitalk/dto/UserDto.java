package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank
    @Max(30)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String error;

    public UserDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
