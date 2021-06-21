package org.junit5.learning.example.shopping;

import lombok.Data;
import org.junit5.learning.example.shopping.model.Cart;
import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.model.Order;
import org.junit5.learning.example.shopping.service.CartService;
import org.junit5.learning.example.shopping.service.OrderService;
import org.junit5.learning.example.shopping.service.WarehouseService;

import java.util.concurrent.locks.ReentrantLock;

@Data
public class ShoppingConsole implements CartService, OrderService {

    private CartService cartService;

    private OrderService orderService;

    private WarehouseService<Goods> warehouseService;

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void clearGoods(Cart cart) {
        cartService.clearGoods(cart);
    }

    @Override
    public void clearGoods(Cart cart, Goods goods) {
        cartService.clearGoods(cart, goods);
    }

    @Override
    public void removeGoods(Cart cart, Goods goods, Integer size) {
        cartService.removeGoods(cart, goods, size);
    }

    @Override
    public void addGoods(Cart cart, Goods goods, Integer size) {
        try {
            lock.lock();
            if (warehouseService.hasInventory(goods, size)) {

                cartService.addGoods(cart, goods, size);

                warehouseService.remove(goods, size);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Order checkout(Cart cart) {
        return cartService.checkout(cart);
    }

    @Override
    public void printOrderDetail(Order order) {
        orderService.printOrderDetail(order);
    }

}
