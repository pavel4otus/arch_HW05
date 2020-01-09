package ru.pavel2107.arch.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.catalog.domain.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
