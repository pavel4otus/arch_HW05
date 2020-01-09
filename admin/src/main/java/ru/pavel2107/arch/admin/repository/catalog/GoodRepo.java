package ru.pavel2107.arch.admin.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.admin.domain.catalog.Brand;
import ru.pavel2107.arch.admin.domain.catalog.Category;
import ru.pavel2107.arch.admin.domain.catalog.Good;

import java.util.List;

public interface GoodRepo extends JpaRepository<Good, Long> {
    List<Good> findByName(String name);

    List<Good> findByCategory(Category category);

    List<Good> findByBrand(Brand brand);

    Good findBySkud(Long skud);
}
