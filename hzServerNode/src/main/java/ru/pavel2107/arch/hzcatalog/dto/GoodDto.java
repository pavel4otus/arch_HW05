package ru.pavel2107.arch.hzcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pavel2107.arch.hzcatalog.domain.WareHouse;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto implements Serializable {
    private Long id;
    private Long skud;
    private String name;
    private String description;
    private Long category;
    private Long brand;
    private Set<WareHouse> wareHouses = new HashSet<>();
}
