package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateEmailRequest {
    @Email(message = "Email should be valid")
    private String newEmail;
}
