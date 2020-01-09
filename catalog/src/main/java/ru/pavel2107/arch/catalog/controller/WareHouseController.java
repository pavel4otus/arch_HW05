package ru.pavel2107.arch.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.pavel2107.arch.catalog.domain.WareHouse;
import ru.pavel2107.arch.catalog.service.WareHouseService;

import java.util.List;

@RestController
public class WareHouseController {

    private WareHouseService service;

    @Autowired
    public WareHouseController(WareHouseService service) {
        this.service = service;
    }

    @GetMapping(value = "/microservices/v1/catalog/warehouses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WareHouse> wareHouseList() {
        List<WareHouse> list = service.findAll();
        return list;
    }
}
