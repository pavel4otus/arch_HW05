package ru.pavel2107.arch.createschema.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_method")
@Data
@NoArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "payment_url")
    private String paymentUrl;

    //@OneToMany( mappedBy = "paymentMethod")
    //private Set<PaymentMethod> orders = new HashSet<>();

}

