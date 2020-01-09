package ru.pavel2107.arch.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.pavel2107.arch.order.domain.Good;
import ru.pavel2107.arch.order.domain.Order;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude
    @Getter
    @Setter
    private Order order;

    @Column(name = "quantity")
    @Getter
    @Setter
    private Integer quantity;

    @Column(name = "price")
    @Getter
    @Setter
    private Double price;

    @ManyToOne
    @JoinColumn(name = "good_id")
    @Getter
    @Setter
    private Good good;

    public OrderItem(Order order, Good good) {
        this.id = 0L;
        this.order = order;
        this.quantity = 0;
        this.price = 0d;
        this.good = good;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
