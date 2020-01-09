package ru.pavel2107.arch.basket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "typeprice")
@NoArgsConstructor
public class TypePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return "TypePrice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
