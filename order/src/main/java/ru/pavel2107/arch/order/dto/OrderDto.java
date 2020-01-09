package ru.pavel2107.arch.order.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pavel2107.arch.order.domain.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private UserDto user;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private String deliveryAddress;
    private Set<StatusHistory> history = new HashSet<>();
    private Set<OrderItem> items = new HashSet<>();
}

