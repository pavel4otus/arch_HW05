package ru.pavel2107.arch.admin.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.admin.domain.catalog.WareHouse;

public interface WareHouseRepo extends JpaRepository<WareHouse, Long> {
}
