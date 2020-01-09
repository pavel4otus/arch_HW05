package ru.pavel2107.arch.admin.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.admin.domain.catalog.Category;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

    Category findByCode(String code);
}
