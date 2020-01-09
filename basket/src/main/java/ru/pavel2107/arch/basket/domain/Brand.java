package ru.pavel2107.arch.basket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    String name;

    @Column(name = "code")
    @Getter
    @Setter
    String code;

    @Column(name = "description")
    @Getter
    @Setter
    String description;

    @Column(name = "logo")
    @Getter
    @Setter
    String logoUrl;


    //   @OneToMany( mappedBy = "brand", fetch = FetchType.LAZY)
    //   @JsonIgnore
//    @ToString.Exclude
//   private Set<Good> goods = new HashSet<>();


    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
