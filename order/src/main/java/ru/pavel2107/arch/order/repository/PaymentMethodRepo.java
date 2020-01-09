package ru.pavel2107.arch.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.order.domain.PaymentMethod;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
}
