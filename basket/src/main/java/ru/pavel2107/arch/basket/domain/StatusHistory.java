package ru.pavel2107.arch.basket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "status_history")
@NoArgsConstructor
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "dateFrom")
    @Getter
    @Setter
    LocalDateTime dateFrom;

    @Column(name = "dateTo")
    @Getter
    @Setter
    LocalDateTime dateTo;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @Getter
    @Setter
    private Order order;

    @Override
    public String toString() {
        return "StatusHistory{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", address='" + address + '\'' +
                '}';
    }
}
