package org.junit5.learning.example.shopping.service;

/**
 * WarehouseService
 *
 * Used to simulate the goods warehouse features.
 *
 * @param <T>
 */
public interface WarehouseService<T> {

    boolean hasInventory(T goods, Integer amount);

    Integer getInventory(T goods);

    void remove(T goods, Integer count);

    void restock();

}
