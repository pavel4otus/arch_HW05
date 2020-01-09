package ru.pavel2107.arch.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ru.pavel2107.arch.catalog.domain.Brand;
import ru.pavel2107.arch.catalog.domain.Category;
import ru.pavel2107.arch.catalog.domain.Good;

import java.util.List;

@NoRepositoryBean
public interface GoodRepo<T extends Good> extends JpaRepository<T, Long> {
    List<T> findByName(String name);

    List<T> findByCategory(Long category);

    List<T> findByBrand(Long brand);

    T findBySkud(Long skud);
}
