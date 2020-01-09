package ru.pavel2107.arch.catalog.service;

import ru.pavel2107.arch.catalog.domain.*;
import ru.pavel2107.arch.catalog.dto.GoodDto;

import java.util.List;

public interface GoodsService {
    GoodDto find(User user, Long id);

    List<GoodDto> findByName(User user, String name);

    List<GoodDto> findByCategory(User user, Long categoryId);

    List<GoodDto> findByBrand(User user, Long brandId);

    List<GoodDto> findAll(User user);
}
