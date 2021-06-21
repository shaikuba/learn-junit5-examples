package org.junit5.learning.example.shopping.service.impl;


import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.service.WarehouseService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ray on 2019/7/30.
 */
public class MobileWarehouseService implements WarehouseService<Goods> {

    private final static Map<Goods, Integer> INVENTORIES = new HashMap();

    static {
        INVENTORIES.put(Goods.newGoods("HUAWEI", BigDecimal.valueOf(1000.0)), 100);
        INVENTORIES.put(Goods.newGoods("XIAOMI", BigDecimal.valueOf(800.0)), 100);
        INVENTORIES.put(Goods.newGoods("VIVO", BigDecimal.valueOf(700.0)), 100);
    }

    private static final Object mutex = new byte[1];

    @Override
    public boolean hasInventory(Goods goods, Integer amount) {
        synchronized (mutex) {
            return INVENTORIES.getOrDefault(goods, 0) >= amount;
        }
    }

    @Override
    public Integer getInventory(Goods goods) {
        synchronized (mutex) {
            return INVENTORIES.getOrDefault(goods, 0);
        }
    }

    @Override
    public void remove(Goods goods, Integer count) {
        synchronized (mutex) {
            INVENTORIES.computeIfPresent(goods, (k, v) -> v - count);
        }
    }

    @Override
    public void restock() {
        synchronized (mutex) {
            INVENTORIES.put(Goods.newGoods("HUAWEI", BigDecimal.valueOf(1000.0)), 100);
            INVENTORIES.put(Goods.newGoods("XIAOMI", BigDecimal.valueOf(800.0)), 100);
            INVENTORIES.put(Goods.newGoods("VIVO", BigDecimal.valueOf(700.0)), 100);
        }
    }
}
