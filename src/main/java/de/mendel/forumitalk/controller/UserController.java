package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.ChangePasswordRequest;
import de.mendel.forumitalk.dto.UserRegistrationRequest;
import de.mendel.forumitalk.dto.UserUpdateEmailRequest;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        try {
            service.registerNewUser(userRegistrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }

    @PostMapping("/profile/change-password")
    public ResponseEntity<String> changePassword(@RequestBody User user, @RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            service.changeUserPassword(user.getUser_id(), changePasswordRequest);
            return ResponseEntity.ok("Password changed successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }

    @PutMapping("/profile/update-email")
    public ResponseEntity<String> updateUserEmail(@RequestBody UserUpdateEmailRequest userUpdateEmailRequest, @RequestBody User user) {
        try {
            service.updateUserEmail(user.getUser_id(), userUpdateEmailRequest);
            return ResponseEntity.ok("Email updated successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }
}
