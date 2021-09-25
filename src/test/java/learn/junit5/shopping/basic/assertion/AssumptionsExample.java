package learn.junit5.shopping.basic.assertion;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Slf4j
public class AssumptionsExample {

    @Test
    void assertTrueTest() {

        assertTrue(false);

        log.info("====");
    }

    @Test
    void assumeTrueTest() {

        assumeTrue(false);

        log.info("====");
    }

    @Test
    void assumingThatTest() {

        assumingThat(() -> false, () -> System.out.println("111"));

        log.info("====");
    }

}
