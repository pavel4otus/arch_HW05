package ru.pavel2107.arch.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.order.domain.DeliveryMethod;

public interface DeliveryMethodRepo extends JpaRepository<DeliveryMethod, Long> {
}
