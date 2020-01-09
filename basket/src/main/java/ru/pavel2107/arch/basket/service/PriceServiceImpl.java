package ru.pavel2107.arch.basket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Price;
import ru.pavel2107.arch.basket.domain.TypePrice;
import ru.pavel2107.arch.basket.repository.PriceRepo;

import java.time.LocalDate;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepo repository;

    @Autowired
    public PriceServiceImpl(PriceRepo repository) {
        this.repository = repository;
    }

    @Override
    public Price getPrice(LocalDate d1, LocalDate d2, Good good, TypePrice typePrice) {
        return repository.findPriceByDateFromBeforeAndDateToAfterAndGoodAndTypePrice(d1, d2, good, typePrice);
    }
}
