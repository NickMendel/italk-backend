package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User mapToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
    }

    public List<UserDto> MapToDtoList(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();
        users.forEach((user -> usersDto.add(mapToDto(user))));
        return usersDto;
    }
}
