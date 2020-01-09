package ru.pavel2107.arch.admin.domain.catalog;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pricelist")
@Data
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Good good;

    @ManyToOne
    @JoinColumn(name = "typeprice_id")
    private TypePrice typePrice;

    @Column(name = "datefrom")
    private LocalDate dateFrom;

    @Column(name = "dateTo")
    private LocalDate dateTo;

    @OneToOne
    private WareHouse wareHouse;

    @Column(name = "price")
    private Double price;
}
