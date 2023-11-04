package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateEmailRequest {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String newEmail;
}
