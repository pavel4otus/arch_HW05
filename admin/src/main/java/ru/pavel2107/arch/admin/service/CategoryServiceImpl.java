package ru.pavel2107.arch.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.admin.domain.catalog.Category;
import ru.pavel2107.arch.admin.repository.catalog.CategoryRepo;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepo repository) {
        this.repository = repository;
    }

    @Override
    public Category findByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public Category save(Category Category) {
        return repository.save(Category);
    }

    @Override
    public void delete(Long id) {
        Category Category = find(id);
        if (Category != null) {
            repository.delete(Category);
        }
    }

    @Override
    public List<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Category find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
