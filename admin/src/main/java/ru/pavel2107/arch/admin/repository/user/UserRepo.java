package ru.pavel2107.arch.admin.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pavel2107.arch.admin.domain.user.User;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByFio(String fio);

    List<User> findByPhone(String fio);
}
