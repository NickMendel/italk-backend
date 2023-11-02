package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserRegistrationRequest {

    private final String username = "admin";
    private String email = "admin@admin.com";
    private String newPassword = "admin";
    private final UserRole role = UserRole.ADMIN;
}
