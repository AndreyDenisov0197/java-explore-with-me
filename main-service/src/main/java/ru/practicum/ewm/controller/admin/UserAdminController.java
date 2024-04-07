package ru.practicum.ewm.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.user.NewUserRequest;
import ru.practicum.ewm.dto.user.UserDto;
import ru.practicum.ewm.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/users")
@Validated
@Slf4j
public class UserAdminController {
    private UserService userService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(required = false)List<Long> ids,
                                  @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                  @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("GET запрос на получение списка пользователей: id = {}, from = {}, size = {}", ids, from, size);
        return userService.getListUsers(ids, from, size);
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid NewUserRequest user) {
        log.info("POST запрос на создание пользователя: {}", user);
        return userService.addNewUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        log.info("DELETE запрос на удаление пользователя c ID = {}", userId);
        userService.deleteUser(userId);
    }
}
