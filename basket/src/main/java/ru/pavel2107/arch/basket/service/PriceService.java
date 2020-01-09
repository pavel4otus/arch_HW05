package ru.pavel2107.arch.basket.service;

import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Price;
import ru.pavel2107.arch.basket.domain.TypePrice;

import java.time.LocalDate;

public interface PriceService {
    Price getPrice(LocalDate d1, LocalDate d2, Good good, TypePrice typePrice);
}
