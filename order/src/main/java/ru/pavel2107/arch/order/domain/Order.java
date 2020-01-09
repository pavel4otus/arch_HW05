package ru.pavel2107.arch.order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @Getter
    @Setter
    private User user;

    @Column(name = "delivery_address")
    @Getter
    @Setter
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    @JsonBackReference
    @Getter
    @Setter
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "delivery_method_id")
    @JsonBackReference
    @Getter
    @Setter
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Getter
    @Setter
    private Set<StatusHistory> history = new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonBackReference
    @Getter
    @Setter
    private Set<OrderItem> items = new HashSet<>();

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
