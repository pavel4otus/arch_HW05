package ru.pavel2107.arch.admin.service;


import ru.pavel2107.arch.admin.domain.catalog.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category Category);

    void delete(Long id);

    Category find(Long id);

    Category findByCode(String code);

    List<Category> findByName(String name);

    List<Category> findAll();
}
