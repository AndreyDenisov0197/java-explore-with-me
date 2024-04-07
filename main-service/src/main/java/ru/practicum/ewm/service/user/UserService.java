package ru.practicum.ewm.service.user;

import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getListUsers(List<Long> ids, Integer from, Integer size);

    UserDto addNewUser(NewUserRequest user);

    void deleteUser(Long userId);
}
