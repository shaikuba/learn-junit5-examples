package learn.junit5.shopping.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.MethodName.class)
public class MethodNameExample {

    @Test
    void order_1() {
    }

    @Test
    void order_3() {
    }

    @DisplayName("order_999")
    @Test
    void order_2() {
    }

    @Test
    void order_4() {
    }

    @Test
    void order_5() {
    }
}
