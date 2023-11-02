package de.mendel.forumitalk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordRequest {
    private String oldPassword;
    @NotBlank(message = "Password is mandatory")
    @Min(value = 8, message = "Password must be at least 8 characters")
    private String newPassword;
}
