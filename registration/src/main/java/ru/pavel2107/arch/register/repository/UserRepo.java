package ru.pavel2107.arch.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.register.domain.User;


public interface UserRepo extends JpaRepository<User, Long> {
}
