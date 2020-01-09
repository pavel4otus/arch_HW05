package ru.pavel2107.arch.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pavel2107.arch.catalog.domain.Brand;
import ru.pavel2107.arch.catalog.domain.Category;
import ru.pavel2107.arch.catalog.domain.Good;
import ru.pavel2107.arch.catalog.service.BrandService;
import ru.pavel2107.arch.catalog.service.CategoryService;
import ru.pavel2107.arch.catalog.service.GoodsService;

import java.util.List;

@RestController
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/microservices/v1/catalog/brands", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brand> getBrands() {
        List<Brand> list = brandService.findAll();
        return list;
    }

}
