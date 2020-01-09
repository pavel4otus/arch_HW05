package ru.pavel2107.arch.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "schedule")
    private String schedule;

    private String address;

    @ManyToMany(mappedBy = "wareHouses")
    @JsonIgnore
    private Set<Good> goods = new HashSet<>();
}
