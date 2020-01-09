package ru.pavel2107.arch.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.order.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
