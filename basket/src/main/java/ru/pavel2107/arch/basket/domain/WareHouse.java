package ru.pavel2107.arch.basket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouses")
@NoArgsConstructor
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "contacts")
    @Getter
    @Setter
    private String contacts;

    @Column(name = "schedule")
    @Getter
    @Setter
    private String schedule;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @ManyToMany(mappedBy = "wareHouses")
    @JsonIgnore
    @Getter
    @Setter
    private Set<Good> goods = new HashSet<>();

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts='" + contacts + '\'' +
                ", schedule='" + schedule + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
