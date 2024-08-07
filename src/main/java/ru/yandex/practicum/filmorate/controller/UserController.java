package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidateException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final List<User> users = new ArrayList<>();

    @PostMapping
    public User addNewUsers(@Valid @RequestBody User user){
        validateUser(user);
        users.add(user);
        log.info("Пользователь добавлен {}", user);
        return user;
    }
    @PutMapping
    public User update(@Valid @RequestBody User user){
        validateUser(user);
        log.info("Пользователь обновлен: {}", user);
        return user;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return users;
    }
    public void validateUser(User user) {
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidateException("Birthday cannot be in the future");
        }

    }
}
