package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Price {
    private Long id;

    //@OneToOne
    //private Good good;

    private Long good_id;
    private TypePrice typePrice;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private WareHouse wareHouse;
    private Double price;
}
