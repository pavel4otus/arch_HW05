package ru.pavel2107.arch.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pavel2107.arch.order.Utils;
import ru.pavel2107.arch.order.domain.*;
import ru.pavel2107.arch.order.dto.OrderDto;
import ru.pavel2107.arch.order.repository.DeliveryMethodRepo;
import ru.pavel2107.arch.order.repository.OrderRepo;
import ru.pavel2107.arch.order.repository.PaymentMethodRepo;
import ru.pavel2107.arch.order.repository.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private OrderRepo orderRepository;
    private UserRepo userRepository;
    private DeliveryMethodRepo deliveryMethodRepo;
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    public OrderController(OrderRepo orderRepository, UserRepo userRepository,
                           DeliveryMethodRepo deliveryMethodRepo, PaymentMethodRepo paymentMethodRepo) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.deliveryMethodRepo = deliveryMethodRepo;
        this.paymentMethodRepo = paymentMethodRepo;
    }

    @PostMapping(value = "/microservices/v1/order/setaddress")
    public OrderDto setAddress(Principal principal, @RequestParam String address) {
        User user = userRepository.findByEmail(principal.getName());
        Order basket = orderRepository.findByUserAndHistory_Empty(user);
        basket.setDeliveryAddress(address);
        basket = orderRepository.save(basket);
        return Utils.toDto(basket);
    }

    @PostMapping(value = "/microservices/v1/order/setpaymentmethod")
    public OrderDto setPaymentMethod(Principal principal, @RequestParam Long paymentMethodId) {
        User user = userRepository.findByEmail(principal.getName());
        Order basket = orderRepository.findByUserAndHistory_Empty(user);
        Optional<PaymentMethod> paymentMethod = paymentMethodRepo.findById(paymentMethodId);
        if (paymentMethod.isPresent()) {
            basket.setPaymentMethod(paymentMethod.get());
        }
        basket = orderRepository.save(basket);
        return Utils.toDto(basket);
    }

    @PostMapping(value = "/microservices/v1/order/setdeliverymethod")
    public OrderDto setDeliveryMethod(Principal principal, @RequestParam Long deliveryMethodId) {
        User user = userRepository.findByEmail(principal.getName());
        Order basket = orderRepository.findByUserAndHistory_Empty(user);
        Optional<DeliveryMethod> deliveryMethod = deliveryMethodRepo.findById(deliveryMethodId);
        if (deliveryMethod.isPresent()) {
            basket.setDeliveryMethod(deliveryMethod.get());
        }
        basket = orderRepository.save(basket);
        return Utils.toDto(basket);
    }


}
