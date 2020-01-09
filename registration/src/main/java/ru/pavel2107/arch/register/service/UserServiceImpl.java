package ru.pavel2107.arch.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.register.domain.User;
import ru.pavel2107.arch.register.repository.UserRepo;


@Service
public class UserServiceImpl implements UserService {

    private UserRepo repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(User user) throws Exception {
        user.setRole("ROLE_USER");
        user = repository.save(user);
        return user;
    }

    @Override
    public User updateExisting(User user) throws Exception {
        user = repository.save(user);
        return user;
    }
}
