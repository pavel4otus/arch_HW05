package ru.pavel2107.arch.basket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.basket.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
