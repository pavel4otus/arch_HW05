package ru.pavel2107.arch.basket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Price;
import ru.pavel2107.arch.basket.domain.TypePrice;

import java.time.LocalDate;


public interface PriceRepo extends JpaRepository<Price, Long> {
    Price findPriceByDateFromBeforeAndDateToAfterAndGoodAndTypePrice(LocalDate d1, LocalDate d2, Good good, TypePrice typePrice);
}
