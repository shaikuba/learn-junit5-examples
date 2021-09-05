package learn.junit5.shopping.basic.naming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Some amazing testcase")
public class DisplayExample {

    @Test
    @DisplayName("test one")
    void testOne() {
    }

    @Test
    @DisplayName("test two")
    void testTwo() {
    }

}
