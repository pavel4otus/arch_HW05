package ru.pavel2107.arch.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pavel2107.arch.basket.domain.OrderItem;
import ru.pavel2107.arch.basket.domain.StatusHistory;
import ru.pavel2107.arch.basket.domain.User;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private User user;
    //private PaymentMethod paymentMethod;
    //private DeliveryMethod deliveryMethod;
    private String deliveryAddress;
    private Set<StatusHistory> history = new HashSet<>();
    private Set<OrderItem> items = new HashSet<>();
}

