package ru.pavel2107.arch.catalog.service;

import ru.pavel2107.arch.catalog.domain.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(Long id);
}
