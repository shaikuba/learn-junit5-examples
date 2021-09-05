package learn.junit5.shopping.basic.naming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(value = DisplayNameGenerator.Simple.class)
@DisplayName("Some amazing testcase")
public class DisplayNameGeneratorExample {

    @Test
    @DisplayName("test one case")
    void testOne() {
    }

    @Test
    @DisplayName("test two case")
    void testTwo() {
    }

}
