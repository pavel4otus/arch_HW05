package ru.pavel2107.arch.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pavel2107.arch.catalog.domain.Brand;
import ru.pavel2107.arch.catalog.domain.Category;
import ru.pavel2107.arch.catalog.domain.WareHouse;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto {
    private Long id;
    private Long skud;
    private String name;
    private String description;
    private Long category;
    private Long brand;
    private Set<WareHouse> wareHouses = new HashSet<>();
}
