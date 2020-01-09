package ru.pavel2107.arch.catalog;

import ru.pavel2107.arch.catalog.domain.Good;
import ru.pavel2107.arch.catalog.dto.GoodDto;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {


    public static GoodDto toDto(Good x) {
        return new GoodDto(x.getId(), x.getSkud(), x.getName(), x.getDescription(),
                x.getCategory(), x.getBrand(), x.getWareHouses());
    }

    public static List<GoodDto> toDtoList(List<Good> goods) {
        List<GoodDto> dtos = goods
                .stream()
                .map(g -> toDto(g))
                .collect(Collectors.toList());
        return dtos;
    }
}
