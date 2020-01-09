package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class WareHouse implements Serializable {
    private Long id;
    private String name;
    private String contacts;
    private String schedule;
    private String address;

    //@ManyToMany( mappedBy = "wareHouses")
    //@JsonIgnore
    //private Set<Long> goods = new HashSet<>();
}
