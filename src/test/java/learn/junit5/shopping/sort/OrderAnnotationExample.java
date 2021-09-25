package learn.junit5.shopping.sort;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class OrderAnnotationExample {

    @Order(1)
    @Test
    void order_1() {
    }

    @Order(2)
    @Test
    void order_3() {
    }

    @Order(3)
    @Test
    void order_2() {
    }

    @Order(4)
    @Test
    void order_4() {
    }

    @Order(5)
    @Test
    void order_5() {
    }
}
