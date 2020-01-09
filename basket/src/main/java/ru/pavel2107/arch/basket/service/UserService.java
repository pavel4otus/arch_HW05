package ru.pavel2107.arch.basket.service;


import ru.pavel2107.arch.basket.domain.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
}
