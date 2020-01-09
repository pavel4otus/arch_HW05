package ru.pavel2107.arch.basket.service;


import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Order;
import ru.pavel2107.arch.basket.domain.User;


public interface BasketService {
    Order findBasket(User user);

    Order save(Order order);

    Order addToBasket(User user, Good good, Integer count);

    Order removeFromBasket(User user, Good good, Integer count);

    void clearBasket(User user);
}
