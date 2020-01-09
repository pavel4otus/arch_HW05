package ru.pavel2107.arch.order.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "username")
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "fio")
    @Getter
    @Setter
    private String fio;

    @Column(name = "phone")
    @Getter
    @Setter
    private String phone;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "enabled")
    @Getter
    @Setter
    private Boolean active;

    @Column(name = "role")
    @Getter
    @Setter
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @Getter
    @Setter
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
