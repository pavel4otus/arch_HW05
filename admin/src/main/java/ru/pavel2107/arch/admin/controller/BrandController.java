package ru.pavel2107.arch.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.pavel2107.arch.admin.domain.catalog.Brand;
import ru.pavel2107.arch.admin.domain.user.User;
import ru.pavel2107.arch.admin.service.BrandService;

import java.security.Principal;
import java.util.List;

@RestController
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "microservices/v1/admin/brands", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brand> listBrands() {
        List<Brand> list = brandService.findAll();
        return list;
    }

    @DeleteMapping(value = "microservices/v1/admin/brands", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@RequestParam(value = "id") Long id) {
        brandService.delete(id);
    }

    @PostMapping(value = "microservices/v1/admin/brands", produces = MediaType.APPLICATION_JSON_VALUE)
    public Brand save(@RequestBody Brand brand) {
        Brand b = brandService.save(brand);
        return b;
    }
}

