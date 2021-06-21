package org.junit5.learning.example.shopping.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.model.Order;
import org.junit5.learning.example.shopping.model.Person;
import org.junit5.learning.example.shopping.service.OrderService;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public void printOrderDetail(Order order) {
        Long orderId = order.getId();

        Person person = order.getCart().getPerson();

        Map<Goods, Integer> cartGoods = order.getCart().getGoods();

        log.info("Person id: {}, Username: {}, Nickname {}", person.getId(), person.getUsername(), person.getNickname());
        log.info("Order detail, order id {}", orderId);
        log.info("Order detail,");

        cartGoods.forEach((goods, amount) ->
                log.info("Goods name: {}, unit cost: {}, amount: {}, total price: {}"
                        , goods.getName()
                        , goods.getPrice()
                        , amount, goods.getPrice().multiply(BigDecimal.valueOf(amount)))
        );

    }
}
