package learn.junit5.shopping.basic.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class TestInstanceExample {

    public TestInstanceExample() {
        log.info("init constructor.");
    }

    @BeforeAll
    static void before_all() {
        log.info("before all");
    }

    @AfterAll
    static void after_all() {
        log.info("after all");
    }


    @BeforeEach
    void before_each() {
        log.info("before each");
    }

    @AfterEach
    void after_each() {
        log.info("after each");
    }

    @Test
    void test_one() {
        log.info("test_one instance test");
    }

    @Test
    void test_two() {
        log.info("test_two instance test");
    }


}
