package ru.pavel2107.arch.basket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.basket.domain.Good;

import java.util.List;

public interface GoodRepo extends JpaRepository<Good, Long> {
}
