package ru.pavel2107.arch.admin.service;


import ru.pavel2107.arch.admin.domain.catalog.Brand;

import java.util.List;

public interface BrandService {
    Brand save(Brand brand);

    void delete(Long id);

    Brand find(Long id);

    Brand findByCode(String code);

    List<Brand> findByName(String name);

    List<Brand> findAll();
}
