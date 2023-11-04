package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dto.*;
import de.mendel.forumitalk.exceptions.EmailInUseException;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.exceptions.UsernameInUseException;
import de.mendel.forumitalk.mapper.UserMapper;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public void registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.findByUsername(userRegistrationRequest.getUsername()) == null) {
            if (userRepository.findByEmail(userRegistrationRequest.getEmail()) == null) {
                String newPassword = userRegistrationRequest.getNewPassword();
                if (newPassword == null || newPassword.isEmpty()) {
                    throw new IllegalArgumentException("Password cannot be null or empty!");
                }
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
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        return userMapper.mapToDto(user)
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found!");
        } else {
            return userMapper.mapListToDto(users));
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        userRepository.delete(user);
    }

    @Transactional
    public void updateUserEmail(Long id, UserUpdateEmailRequest userUpdateEmailRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        if (userRepository.findByEmail(userUpdateEmailRequest.getNewEmail()) == null || user.getEmail().equals(userUpdateEmailRequest.getNewEmail())) {
            user.setEmail(userUpdateEmailRequest.getNewEmail());
            userRepository.save(user);
        } else {
            throw new EmailInUseException("Email already taken!");
        }
    }

    @Transactional
    public void changeUserPassword(Long id, ChangePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID: " + id + " does not exist!"));
        String hashedOldPassword = passwordEncoder.encode(request.getOldPassword());
        if (!user.getPassword().matches(hashedOldPassword)) {
            throw new NotFoundException("Old password is incorrect!");
        }
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public UserDto findUserByUsername(String username) {
        return userMapper.mapToDto(userRepository.findByUsername(username));
    }
}


