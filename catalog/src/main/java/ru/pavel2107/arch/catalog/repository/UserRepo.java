package ru.pavel2107.arch.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.catalog.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
