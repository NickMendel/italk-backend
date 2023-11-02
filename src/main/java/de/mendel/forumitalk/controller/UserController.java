package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.ChangePasswordRequest;
import de.mendel.forumitalk.dto.UserRegistrationRequest;
import de.mendel.forumitalk.dto.UserUpdateEmailRequest;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        service.registerNewUser(userRegistrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User created successfully!");
    }

    @PostMapping("/{id}/profile/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest changePasswordRequest) {
        service.changePassword(id, changePasswordRequest);
        return ResponseEntity.ok("Password changed successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/profile/update-email")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserUpdateEmailRequest userUpdateEmailRequest) {
        User currentUser = service.getUserById(id);
        service.updateUserEmail(currentUser, userUpdateEmailRequest);
        return ResponseEntity.ok("User updated successfully!");
    }
}
