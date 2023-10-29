package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.UserDto;
import de.mendel.forumitalk.exceptions.EmailInUseException;
import de.mendel.forumitalk.exceptions.UsernameInUseException;
import de.mendel.forumitalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            if (service.isUsernameTaken(userDto.getUsername())) {
                throw new UsernameInUseException("Username is already taken");
            }

            if (service.isEmailTaken(userDto.getEmail())) {
                throw new EmailInUseException("Email is already in use");
            }
            UserDto createdUser = service.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UsernameInUseException | EmailInUseException e) {
            UserDto errorDto = new UserDto();
            errorDto.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = service.getUsersById(id);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedUser = service.updateUser(userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }
}
