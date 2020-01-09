package ru.pavel2107.arch.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "goods_on_warehouses")
@Data
@NoArgsConstructor
public class GoodOnWareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "warehouse_id")
    private Long wareHouseId;

    @Column(name = "qnty")
    private Long quantity;
}
