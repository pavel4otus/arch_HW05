package ru.pavel2107.arch.basket.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pricelist")
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @OneToOne
    @Getter
    @Setter
    private Good good;

    @ManyToOne
    @JoinColumn(name = "typeprice_id")
    @Getter
    @Setter
    private TypePrice typePrice;

    @Column(name = "datefrom")
    @Getter
    @Setter
    private LocalDate dateFrom;

    @Column(name = "dateTo")
    @Getter
    @Setter
    private LocalDate dateTo;

    @OneToOne
    @Getter
    @Setter
    private WareHouse wareHouse;

    @Column(name = "price")
    @Getter
    @Setter
    private Double price;
}
