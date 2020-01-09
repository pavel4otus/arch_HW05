package ru.pavel2107.arch.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.catalog.domain.WareHouse;

public interface StorageRepo extends JpaRepository<WareHouse, Long> {
}
