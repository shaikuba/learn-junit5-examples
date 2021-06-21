package org.junit5.learning.example.shopping.service.impl;


import org.junit5.learning.example.shopping.model.Cart;
import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.model.Order;
import org.junit5.learning.example.shopping.service.CartService;

public class CartServiceImpl implements CartService {

    @Override
    public void clearGoods(Cart cart) {
        cart.getGoods().clear();
    }

    @Override
    public void clearGoods(Cart cart, Goods goods) {
        cart.getGoods().remove(goods);
    }

    @Override
    public void removeGoods(Cart cart, Goods goods, Integer size) {
        cart.getGoods().computeIfPresent(goods, (k, oldVSize) -> oldVSize > size ? oldVSize - size : null);
    }

    @Override
    public void addGoods(Cart cart, Goods goods, Integer size) {
        cart.getGoods().computeIfPresent(goods, (k, oldVSize) -> oldVSize == null ? 0 : oldVSize + size);
        cart.getGoods().computeIfAbsent(goods, k -> size);
    }

    @Override
    public Order checkout(Cart cart) {
        Order order = new Order();
        order.setCart(cart);
        return order;
    }

}
