package ru.pavel2107.arch.admin.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.admin.domain.catalog.GoodOnWareHouse;

public interface GoodOnWareHouseRepo extends JpaRepository<GoodOnWareHouse, Long> {
}
