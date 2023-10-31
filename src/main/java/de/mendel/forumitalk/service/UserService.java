package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dao.UserDao;
import de.mendel.forumitalk.dto.UserDto;
import de.mendel.forumitalk.dto.UserMapper;
import de.mendel.forumitalk.exceptions.EmailInUseException;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.exceptions.UsernameInUseException;
import de.mendel.forumitalk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        User existingUser = userDao.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            throw new UsernameInUseException("Username already taken!");
        }
        User existingEmail = userDao.findByEmail(userDto.getEmail());
        if (existingEmail != null) {
            throw new EmailInUseException("Email already taken!");
        }
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
        User existingUser = userDao.findById(userDto.getUser_id());
        if (existingUser != null) {
            User userWithNewEmail = userDao.findByEmail(userDto.getEmail());
            if (userWithNewEmail != null && !userWithNewEmail.getUser_id().equals(userDto.getUser_id())) {
                throw new EmailInUseException("Email already taken!");
            }

            existingUser.setEmail(userDto.getEmail());
            existingUser.setPassword(userDto.getPassword());

            User savedUser = userDao.save(existingUser);
            return userMapper.mapToDto(savedUser);
        } else {
            throw new NotFoundException("User with ID: " + userDto.getUser_id() + " does not exist!");
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


