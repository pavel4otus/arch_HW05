package ru.pavel2107.arch.catalog.service;


import ru.pavel2107.arch.catalog.domain.User;

public interface UserService {
    User findByEmail(String email);
}
