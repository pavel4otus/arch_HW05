package ru.pavel2107.arch.order;


import ru.pavel2107.arch.order.domain.*;
import ru.pavel2107.arch.order.dto.GoodDto;
import ru.pavel2107.arch.order.dto.OrderDto;
import ru.pavel2107.arch.order.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {


    public static GoodDto toDto(Good x) {
        return new GoodDto(x.getId(), x.getSkud(), x.getName(), x.getDescription(),
                x.getCategory(), x.getBrand(), x.getWareHouses());
    }


    public static List<GoodDto> toGoodDtoList(List<Good> goods) {
        List<GoodDto> dtos = goods
                .stream()
                .map(g -> toDto(g))
                .collect(Collectors.toList());
        return dtos;
    }


    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), toDto(order.getUser()), order.getPaymentMethod(),
                order.getDeliveryMethod(), order.getDeliveryAddress(),
                order.getHistory(), order.getItems());
    }

    public static List<OrderDto> toOrderDtoList(List<Order> orders) {
        List<OrderDto> dtos = orders
                .stream()
                .map(g -> toDto(g))
                .collect(Collectors.toList());
        return dtos;
    }

    public static UserDto toDto(User user) {
        String password = "";
        return new UserDto(user.getId(), user.getEmail(), password, user.getFio(), user.getPhone(),
                user.getActive(), user.getRole(), user.getAddress(), user.getOrders());
    }

    public static List<UserDto> toUserDtoList(List<User> users) {
        List<UserDto> dtos = users
                .stream()
                .map(g -> toDto(g))
                .collect(Collectors.toList());
        return dtos;
    }

}




