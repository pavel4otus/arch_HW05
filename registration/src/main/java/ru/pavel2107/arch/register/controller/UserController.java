package ru.pavel2107.arch.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.pavel2107.arch.register.domain.User;
import ru.pavel2107.arch.register.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/microservices/v1/users/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) throws Exception {
        User newUser = service.registerNewUser(user);
        return newUser;
    }

    @PutMapping(value = "/microservices/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateExisting(@RequestBody User user) throws Exception {
        User newUser = service.updateExisting(user);
        return newUser;
    }
}

