package org.junit5.learning.example.shopping.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    public static Goods newGoods(String name, BigDecimal price) {
        Goods goods = new Goods();

        goods.setName(name);
        goods.setPrice(price);

        return goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Goods goods = (Goods) o;

        return name.equals(goods.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
