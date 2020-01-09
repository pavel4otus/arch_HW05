package ru.pavel2107.arch.basket;


import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Order;
import ru.pavel2107.arch.basket.dto.GoodDto;
import ru.pavel2107.arch.basket.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getUser(), order.getDeliveryAddress(),
                order.getHistory(), order.getItems());
    }

    public static List<OrderDto> toOrderDtoList(List<Order> orders) {
        List<OrderDto> dtos = orders
                .stream()
                .map(g -> toDto(g))
                .collect(Collectors.toList());
        return dtos;
    }
}


