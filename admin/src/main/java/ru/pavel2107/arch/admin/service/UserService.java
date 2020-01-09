package ru.pavel2107.arch.admin.service;


import ru.pavel2107.arch.admin.domain.user.User;

import java.util.List;

public interface UserService {
    User updateExisting(User user) throws Exception;

    void disable(User user);

    void enable(User user);

    User findByEmail(String email);

    User find(Long id);

    List<User> findByFio(String fio);

    List<User> findByPhone(String phone);

    User save(User user);

    List<User> findAll();
}
