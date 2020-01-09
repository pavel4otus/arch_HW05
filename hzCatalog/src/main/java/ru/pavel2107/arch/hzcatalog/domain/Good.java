package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Good {
    private Long id;
    private Long skud;
    private String name;
    private String description;

    //@ManyToOne
    //@JoinColumn( name = "category_id")
    //private Category category;
    private Long category;

    //@ManyToOne
    //@JoinColumn( name = "brand_id")
    //private Brand brand;
    private Long brand;


//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(
//            name = "goods_on_warehouses",
//            joinColumns = {@JoinColumn(name = "good_id")},
//            inverseJoinColumns = {@JoinColumn(name = "warehouse_id")}
//    )
    private Set<WareHouse> wareHouses = new HashSet<>();
}
