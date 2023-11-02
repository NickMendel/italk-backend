package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dto.AdminUserRegistrationRequest;
import de.mendel.forumitalk.dto.ChangePasswordRequest;
import de.mendel.forumitalk.dto.UserRegistrationRequest;
import de.mendel.forumitalk.dto.UserUpdateEmailRequest;
import de.mendel.forumitalk.exceptions.EmailInUseException;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.exceptions.UsernameInUseException;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new org.springframework.security.core.userdetails.User("admin", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new org.springframework.security.core.userdetails.User("user", "user", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.findByUsername(userRegistrationRequest.getUsername()) == null) {
            if (userRepository.findByEmail(userRegistrationRequest.getEmail()) == null) {
                String hashedPassword = passwordEncoder.encode(userRegistrationRequest.getNewPassword());
                User newUser = new User();
                newUser.setUsername(userRegistrationRequest.getUsername());
                newUser.setEmail(userRegistrationRequest.getEmail());
                newUser.setPassword(hashedPassword);
                userRepository.save(newUser);
            } else {
                throw new EmailInUseException("Email already taken!");
            }
        } else {
            throw new UsernameInUseException("Username already taken!");
        }
    }

    @Transactional
    public void createNewAdminUser(AdminUserRegistrationRequest adminUserRegistrationRequest) {
        String hashedPassword = passwordEncoder.encode(adminUserRegistrationRequest.getNewPassword());
        User newAdminUser = new User();
        newAdminUser.setUsername(adminUserRegistrationRequest.getUsername());
        newAdminUser.setEmail(adminUserRegistrationRequest.getEmail());
        newAdminUser.setPassword(hashedPassword);
        newAdminUser.setRole(adminUserRegistrationRequest.getRole());
        userRepository.save(newAdminUser);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found!");
        } else {
            return users;
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUserEmail(User currentUser, UserUpdateEmailRequest userUpdateEmailRequest) {
        if (userRepository.findByEmail(userUpdateEmailRequest.getNewEmail()) == null || currentUser.getEmail().equals(userUpdateEmailRequest.getNewEmail())) {
            currentUser.setEmail(userUpdateEmailRequest.getNewEmail());
            userRepository.save(currentUser);
        } else {
            throw new EmailInUseException("Email already taken!");
        }
    }

    @Transactional
    public User changePassword(Long id, ChangePasswordRequest changePasswordRequest) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        String hashedOldPassword = passwordEncoder.encode(changePasswordRequest.getOldPassword());
        if (!currentUser.getPassword().matches(hashedOldPassword)) {
            throw new NotFoundException("Old password is incorrect!");
        }
        String hashedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        currentUser.setPassword(hashedPassword);
        userRepository.save(currentUser);
        return currentUser;
    }
/*
    public UserDetails findUserByUsername(String username) {
        return APPLICATION_USERS.stream()
                .filter(userDetails -> userDetails.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
 */

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}


