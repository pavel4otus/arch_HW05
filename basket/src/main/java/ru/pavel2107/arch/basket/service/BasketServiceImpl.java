package ru.pavel2107.arch.basket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.arch.basket.domain.*;
import ru.pavel2107.arch.basket.repository.OrderRepo;
import ru.pavel2107.arch.basket.repository.PriceRepo;
import ru.pavel2107.arch.basket.repository.TypePriceRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private OrderRepo repository;
    private PriceRepo priceRepo;
    private TypePriceRepo typePriceRepo;

    @Autowired
    public BasketServiceImpl(OrderRepo repository, PriceRepo priceRepo, TypePriceRepo typePriceRepo) {
        this.repository = repository;
        this.priceRepo = priceRepo;
        this.typePriceRepo = typePriceRepo;
    }

    //
    // корзина это просто заказ без истории состояний
    //
    @Override
    public Order findBasket(User user) {
        Order basket = repository.findByUserAndHistory_Empty(user);
        return basket;
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    //
    // добавляем итем в корзину
    //
    @Override
    public Order addToBasket(User user, Good good, Integer count) {
        Order backet = changeBasket(user, good, count);
        return backet;
    }

    //
    // удаляем итем из корзины
    //
    @Override
    public Order removeFromBasket(User user, Good good, Integer count) {
        return changeBasket(user, good, count);
    }

    private Order changeBasket(User user, Good good, Integer count) {
        Order basket = findBasket(user);
        TypePrice typePrice = typePriceRepo.findById(1L).orElse(new TypePrice());
        if (basket == null) {
            basket = new Order();
            basket.setUser(user);
            basket.setItems(new HashSet<>());
        }
        ;
        OrderItem item = basket.getItems()
                .stream()
                .filter(orderItem -> orderItem.getGood().equals(good))
                .findFirst().orElse(new OrderItem(basket, good));
        item.setQuantity(item.getQuantity() + count);
        if (item.getId() == 0) {
            basket.getItems().add(item);

        }
        if (item.getQuantity() <= 0) {
            basket.getItems().remove(item);
        }
        Price price = priceRepo.findPriceByDateFromBeforeAndDateToAfterAndGoodAndTypePrice(LocalDate.now(), LocalDate.now(), good, typePrice);
        if (price != null) {
            item.setPrice(item.getQuantity() * price.getPrice());
        } else {
            item.setPrice(0d);
        }

        return basket;
    }


    //
    // удаляем содержимое корзины
    //
    @Override
    public void clearBasket(User user) {
        Order basket = findBasket(user);
        for (OrderItem item : basket.getItems()) {
            basket.getItems().remove(item);
        }
    }

}
