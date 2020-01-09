package ru.pavel2107.arch.order.service;

import ru.pavel2107.arch.order.domain.Order;
import ru.pavel2107.arch.order.domain.PaymentMethod;
import ru.pavel2107.arch.order.domain.User;

public interface OrderService {
    Order setAddress(User user, Order order, String address);

    Order setPaymentMethod(User user, Order order, PaymentMethod paymentMethod);

    Order setDeliveryMethod(User user, Order order, PaymentMethod paymentMethod);
}
