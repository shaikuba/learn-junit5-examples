package junit.learning.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class TestObjectExample {

    public TestObjectExample() {
        log.info("TestObjectExample object init...");
    }


    @BeforeEach
    void beforeEach() {
        log.info("beforeEach");
    }
    @AfterEach
    void afterEach() {
        log.info("afterEach");
    }

    @BeforeAll
    void beforeAll() {
        log.info("beforeAll");
    }
    @AfterAll
    void afterAll() {
        log.info("afterAll");
    }


    @Test
    void test1() {
        log.info("test1");
    }

    @Test
    void test2() {
        log.info("test2");
    }

}
