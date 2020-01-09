package ru.pavel2107.arch.catalog.service;

import ru.pavel2107.arch.catalog.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);
}
