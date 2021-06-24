package org.junit5.learning.example.shopping.service;


import org.junit5.learning.example.shopping.model.Cart;
import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.model.Order;

public interface CartService {

    /**
     * Add {@link Goods} to cart
     *
     * @param cart  cart
     * @param goods goods
     * @param size  goods number
     */
    void addGoods(Cart cart, Goods goods, Integer size);

    /**
     * Clear goods by goods type, and specified size.
     *
     * @param cart  cart
     * @param goods specified goods
     * @param size  specified size
     */
    void removeGoods(Cart cart, Goods goods, Integer size);

    /**
     * Clear all goods in the cart
     */
    void clearGoods(Cart cart);

    /**
     * Clear all specified by goods type
     *
     * @param cart  cart
     * @param goods specified goods
     */
    void clearGoods(Cart cart, Goods goods);

    /**
     * Submit order
     *
     * @param cart
     * @return {@link Order}
     */
    Order checkout(Cart cart);

}
