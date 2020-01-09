package ru.pavel2107.arch.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel2107.arch.order.domain.Order;
import ru.pavel2107.arch.order.domain.User;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserAndHistoryIsNotNull(User user);

    Order findByUserAndHistory_Empty(User user);
}
