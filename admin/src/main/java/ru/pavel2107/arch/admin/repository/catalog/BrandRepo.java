package ru.pavel2107.arch.admin.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.admin.domain.catalog.Brand;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    List<Brand> findByName(String name);

    Brand findByCode(String code);
}
