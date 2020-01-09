package ru.pavel2107.arch.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pavel2107.arch.order.domain.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String fio;
    private String phone;
    private Boolean active;
    private String role;
    private String address;
    private Set<Order> orders = new HashSet<>();
}
