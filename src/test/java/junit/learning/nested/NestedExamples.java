package junit.learning.nested;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class NestedExamples {

    @BeforeEach
    void beforeEach() {
        log.info("beforeEach");
    }
    @AfterEach
    void afterEach() {
        log.info("afterEach");
    }

    @Test
    void test1() {
        log.info("test1");
    }

    @Test
    void test2() {
        log.info("test2");
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class NestedExample1 {

        @BeforeEach
        void beforeEach() {
            log.info("NestedExample1.beforeEach");
        }
        @AfterEach
        void afterEach() {
            log.info("NestedExample1.afterEach");
        }

        @BeforeAll
        void beforeAll() {
            log.info("NestedExample1.beforeAll");
        }
        @AfterAll
        void afterAll() {
            log.info("NestedExample1.afterAll");
        }

        @Test
        void test1() {
            log.info("NestedExample1.test1");
        }

        @Test
        void test2() {
            log.info("NestedExample1.test2");
        }
    }


    public static void main(String[] args) {
    }
}




