package ru.pavel2107.arch.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.order.domain.GoodOnWareHouse;

public interface GoodOnWareHouseRepo extends JpaRepository<GoodOnWareHouse, Long> {
}
