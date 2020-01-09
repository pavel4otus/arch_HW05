package ru.pavel2107.arch.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.admin.domain.catalog.Brand;
import ru.pavel2107.arch.admin.domain.catalog.Category;
import ru.pavel2107.arch.admin.domain.catalog.Good;
import ru.pavel2107.arch.admin.repository.catalog.GoodRepo;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodRepo repository;

    @Autowired
    public GoodsServiceImpl(GoodRepo repository) {
        this.repository = repository;
    }

    @Override
    public Good save(Good good) {
        return repository.save(good);
    }

    @Override
    public void delete(Long id) {
        Good good = find(id);
        if (good != null) {
            repository.delete(good);
        }
    }

    @Override
    public Good find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Good> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Good findBySkud(Long skud) {
        return repository.findBySkud(skud);
    }

    @Override
    public List<Good> findAll() {
        return repository.findAll();
    }
}
