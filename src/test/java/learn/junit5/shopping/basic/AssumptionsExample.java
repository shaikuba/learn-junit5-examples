package learn.junit5.shopping.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsExample {

    @Test
    void assertTrueTest() {

        assertTrue(false);

        System.out.println("====");
    }

    @Test
    void assumeTrueTest() {

        assumeTrue(false);

        System.out.println("====");
    }

    @Test
    void assumingThatTest() {

        assumingThat(() -> false, () -> System.out.println("111"));

        System.out.println("====");
    }

}
