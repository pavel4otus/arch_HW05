package ru.pavel2107.arch.basket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pavel2107.arch.basket.Utils;
import ru.pavel2107.arch.basket.domain.Good;
import ru.pavel2107.arch.basket.domain.Order;
import ru.pavel2107.arch.basket.domain.User;
import ru.pavel2107.arch.basket.dto.OrderDto;
import ru.pavel2107.arch.basket.service.BasketService;
import ru.pavel2107.arch.basket.service.GoodsService;
import ru.pavel2107.arch.basket.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class BasketController {
    private BasketService basketService;
    private UserService userService;
    private GoodsService goodsService;


    @Autowired
    public BasketController(BasketService basketService, UserService userService, GoodsService goodsService) {
        this.basketService = basketService;
        this.userService = userService;
        this.goodsService = goodsService;
    }

    @GetMapping(value = "/microservices/v1/basket", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(Principal principal) {
        User user = findUser(principal);
        Order basket = basketService.findBasket(user);
        return basket;
    }

    @PostMapping(value = "/microservices/v1/user/basket/additem", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addItem(Principal principal, Long goodId, Integer count) {
        User user = findUser(principal);
        Good good = goodsService.find(goodId);
        Order basket = basketService.addToBasket(user, good, count);
        basket = basketService.save(basket);
        OrderDto dto = Utils.toDto(basket);
        return dto;
    }

    @PostMapping(value = "/microservices/v1/user/basket/removeitem", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto removeItem(Principal principal, Long goodId, Integer count) {
        User user = findUser(principal);
        Good good = goodsService.find(goodId);
        Order basket = basketService.removeFromBasket(user, good, count);
        basket = basketService.save(basket);
        OrderDto dto = Utils.toDto(basket);
        return dto;
    }

    private User findUser(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return user;
    }
}
