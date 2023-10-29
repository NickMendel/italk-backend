package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dao.UserDao;
import de.mendel.forumitalk.dto.UserDto;
import de.mendel.forumitalk.dto.UserMapper;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.model.User;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {


    private final UserDao userDao;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }


    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        User savedUser = userDao.save(user);

        return userMapper.mapToDto(savedUser);
    }

    public UserDto getUsersById(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new NotFoundException("User not find with ID: " + id);
        }
        return userMapper.mapToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();
        return userMapper.MapToDtoList(users);
    }

    public void deleteUser(Long id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
        } else {
            throw new NotFoundException("User with ID: " + id + " does not exist!");
        }
    }

    public UserDto updateUser(UserDto userDto) {
        User existingUser = userDao.findById(userDto.getId());
        if (existingUser != null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

            if (violations.isEmpty()) {
                existingUser.setUsername(userDto.getUsername());
                existingUser.setEmail(userDto.getEmail());
                existingUser.setPassword(userDto.getPassword());

                User updatedUser = userDao.save(existingUser);
                return userMapper.mapToDto(updatedUser);
            } else {
                throw new ValidationException("Validation failed for user with ID: " + userDto.getId());
            }
        } else {
            throw new NotFoundException("User not found with ID: " + userDto.getId());
        }
    }

    public boolean isUsernameTaken(String username) {
        User existingUser = userDao.findByUsername(username);
        return existingUser != null;
    }

    public boolean isEmailTaken(String email) {
        User existingEmail = userDao.findByEmail(email);
        return existingEmail != null;
    }
}


