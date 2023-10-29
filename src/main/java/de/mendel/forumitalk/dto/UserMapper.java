package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserDto mapToDto(User user) {
        return new UserDto(user.getUser_id(), user.getUsername(), user.getEmail(), user.getPassword(), user.getTopics());
    }

    public User mapToEntity(UserDto userDto) {
        return new User(userDto.getUser_id(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getTopics());
    }

    public List<UserDto> MapToDtoList(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();
        users.forEach((user -> usersDto.add(mapToDto(user))));
        return usersDto;
    }
}
