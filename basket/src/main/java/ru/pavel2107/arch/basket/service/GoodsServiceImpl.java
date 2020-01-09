package ru.pavel2107.arch.basket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.repository.GoodRepo;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodRepo repository;

    @Autowired
    public GoodsServiceImpl(GoodRepo repository) {
        this.repository = repository;
    }


    @Override
    public Good find(Long id) {
        return repository.findById(id).orElse(new Good());
    }

}
