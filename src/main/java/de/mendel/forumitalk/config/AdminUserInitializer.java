package de.mendel.forumitalk.config;

import de.mendel.forumitalk.dto.AdminUserRegistrationRequest;
import de.mendel.forumitalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements ApplicationRunner {

    private final UserService userService;


    @Override
    public void run(ApplicationArguments args) {
        if (userService.findUserByUsername("admin") == null) {
            userService.createNewAdminUser(new AdminUserRegistrationRequest());
        }
    }
}
