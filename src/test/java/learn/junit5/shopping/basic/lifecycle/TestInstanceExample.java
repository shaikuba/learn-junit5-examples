package learn.junit5.shopping.basic.lifecycle;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceExample {

    public TestInstanceExample() {
        System.out.println("init constructor.");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @Test
    void test1() {
        System.out.println("test1 instance test");
    }

    @Test
    void test2() {
        System.out.println("test2 instance test");
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    public class Nested1 {

        public Nested1() {
            System.out.println("initialize nested1");
        }
        //@BeforeAll
        void beforeAll() {
            System.out.println("before all");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("Nested1 before each");
        }

        @Test
        void test1() {
            System.out.println("Nested1 test1 instance test");
        }

        @Test
        void test2() {
            System.out.println("Nested1 test2 instance test");
        }
    }

}
