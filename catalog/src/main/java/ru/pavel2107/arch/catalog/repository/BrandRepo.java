package ru.pavel2107.arch.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.catalog.domain.Brand;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    List<Brand> findByName(String name);
}
