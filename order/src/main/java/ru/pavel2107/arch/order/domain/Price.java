package ru.pavel2107.arch.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "pricelist")
@Data
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "typeprice_id")
    private TypePrice typePrice;

    @Column(name = "datefrom")
    private LocalDate dateFrom;

    @Column(name = "dateTo")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;

    @Column(name = "price")
    private Double price;
}
