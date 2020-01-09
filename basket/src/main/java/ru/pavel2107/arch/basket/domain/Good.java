package ru.pavel2107.arch.basket.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "goods")
@NoArgsConstructor
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "skud")
    @Getter
    @Setter
    private Long skud;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

//    @ManyToOne
//    @JoinColumn( name = "category_id")
//    private Category category;

    //  @ManyToOne
    //@JoinColumn( name = "brand_id")
//    private Brand brand;

    @ManyToMany // ( cascade ={ CascadeType.ALL})
    @JoinTable(
            name = "goods_on_warehouses",
            joinColumns = {@JoinColumn(name = "good_id")},
            inverseJoinColumns = {@JoinColumn(name = "warehouse_id")}
    )
    @Getter
    @Setter
    private Set<WareHouse> wareHouses = new HashSet<>();

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", skud=" + skud +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
