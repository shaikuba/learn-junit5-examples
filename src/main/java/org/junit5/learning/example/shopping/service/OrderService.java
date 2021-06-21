package org.junit5.learning.example.shopping.service;


import org.junit5.learning.example.shopping.model.Order;

/**
 * OrderService
 *
 * Accessor for operating the user order
 */
public interface OrderService {

    /**
     * To print user order details
     *
     * @param order User order
     */
    void printOrderDetail(Order order);

}
