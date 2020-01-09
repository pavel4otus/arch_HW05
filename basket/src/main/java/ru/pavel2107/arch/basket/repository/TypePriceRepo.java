package ru.pavel2107.arch.basket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.basket.domain.TypePrice;

public interface TypePriceRepo extends JpaRepository<TypePrice, Long> {
}
