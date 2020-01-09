package ru.pavel2107.arch.order.service;

import ru.pavel2107.arch.order.domain.Order;
import ru.pavel2107.arch.order.domain.PaymentMethod;
import ru.pavel2107.arch.order.domain.User;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order setAddress(User user, Order order, String address) {
        return null;
    }

    @Override
    public Order setPaymentMethod(User user, Order order, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public Order setDeliveryMethod(User user, Order order, PaymentMethod paymentMethod) {
        return null;
    }
}
