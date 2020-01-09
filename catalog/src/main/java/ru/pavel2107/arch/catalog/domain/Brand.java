package ru.pavel2107.arch.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @Column(name = "description")
    String description;


    @Column(name = "logo")
    String logoUrl;

    //@OneToMany( mappedBy = "brand", fetch = FetchType.LAZY)
    //@JsonIgnore
    //private Set<Good> goods = new HashSet<>();

}
