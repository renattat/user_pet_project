package ru.userpetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.userpetproject.entity.User;
import ru.userpetproject.service.UserService;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            throw new RuntimeException("В БД нет пользователя с id = " + id);
        }
        return user.get();
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        User savedUser = userService.addNewUser(user);
        return savedUser;
    }
}
