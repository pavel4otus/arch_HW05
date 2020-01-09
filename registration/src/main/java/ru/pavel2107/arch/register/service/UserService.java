package ru.pavel2107.arch.register.service;


import ru.pavel2107.arch.register.domain.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user) throws Exception;

    User updateExisting(User user) throws Exception;
}
