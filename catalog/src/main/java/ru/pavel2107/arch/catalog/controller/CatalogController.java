package ru.pavel2107.arch.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.pavel2107.arch.catalog.domain.Brand;
import ru.pavel2107.arch.catalog.domain.Category;
import ru.pavel2107.arch.catalog.domain.Good;
import ru.pavel2107.arch.catalog.domain.User;
import ru.pavel2107.arch.catalog.dto.GoodDto;
import ru.pavel2107.arch.catalog.service.BrandService;
import ru.pavel2107.arch.catalog.service.CategoryService;
import ru.pavel2107.arch.catalog.service.GoodsService;
import ru.pavel2107.arch.catalog.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CatalogController {

    private GoodsService goodsService;
    private UserService userService;

    public CatalogController(GoodsService goodsService, UserService userService){
        this.goodsService = goodsService;
        this.userService     = userService;
    }

    @GetMapping(value = "/microservices/v2/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoods(Principal principal) {
        User user = findUser(principal);
        List<GoodDto> list = goodsService.findAll(user);
        return list;
    }

    @GetMapping(value = "/microservices/v2/catalog/bycategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoodsByCategory(Principal principal, @RequestParam Long categoryId) {
        User user = findUser(principal);
        List<GoodDto> list = goodsService.findByCategory(user, categoryId);
        return list;
    }

    @GetMapping(value = "/microservices/v2/catalog/bybrand", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodDto> listGoodsByBrand(Principal principal, @RequestParam Long brandId) {
        User user = findUser(principal);
        List<GoodDto> list = goodsService.findByBrand(user, brandId);
        return list;
    }

    @GetMapping(value = "/microservices/v2/catalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GoodDto get(Principal principal, @PathVariable(value = "id") Long id) {
        User user = findUser(principal);
        GoodDto good = goodsService.find(user, id);
        return good;
    }

    private User findUser(Principal principal) {
        User user = null;
        if (principal != null) {
            user = userService.findByEmail(principal.getName());
        }
        ;
        return user;
    }
}
