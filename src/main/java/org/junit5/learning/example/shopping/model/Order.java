package org.junit5.learning.example.shopping.model;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private Cart cart;

}
