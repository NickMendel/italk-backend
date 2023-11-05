package de.mendel.forumitalk.mapper;

import de.mendel.forumitalk.dto.UserDto;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto mapToDto(User user) {
        return new UserDto(user.getUsername(), user.getEmail());
    }

    public List<UserDto> mapListToDto(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();
        users.forEach((user -> usersDto.add(mapToDto(user))));
        return usersDto;
    }
}
