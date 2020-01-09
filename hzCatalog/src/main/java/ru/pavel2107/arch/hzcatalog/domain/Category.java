package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Category extends CommonTypeWithId implements Serializable {
   private String name;
    String code;

    //@OneToMany( mappedBy = "category", fetch = FetchType.LAZY)
    //@JsonIgnore
    //private Set<Good> goods = new HashSet<>();
}
