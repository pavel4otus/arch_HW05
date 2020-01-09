package ru.pavel2107.arch.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.admin.domain.user.User;
import ru.pavel2107.arch.admin.repository.user.UserRepo;

import java.util.List;

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
    public User updateExisting(User user) throws Exception {
        return repository.save(user);
    }

    @Override
    public void disable(User user) {
        user.setActive(false);
        repository.save(user);
    }

    @Override
    public void enable(User user) {
        user.setActive(true);
        repository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByFio(String fio) {
        return repository.findByFio(fio);
    }

    @Override
    public List<User> findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
