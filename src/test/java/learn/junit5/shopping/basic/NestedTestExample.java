package learn.junit5.shopping.basic;

import org.junit.jupiter.api.*;

public class NestedTestExample {

    public NestedTestExample() {
        System.out.println("constructor init");
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
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @Nested
    public class Nest1 {

        public Nest1() {
            System.out.println("Nest1 constructor init");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("Nest1 before each");
        }

        @Test
        void test1() {
            System.out.println("Nest1.test1");
        }

        @Test
        void test2() {
            System.out.println("Nest1.test2");
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    public class Nest2 {

        public Nest2() {
            System.out.println("Nest2 constructor init");
        }

        @BeforeAll
        void beforeAll() {
            System.out.println("Nest2 before all");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("Nest2 before each");
        }

        @Test
        void test1() {
            System.out.println("Nest2.test1");
        }

        @Test
        void test2() {
            System.out.println("Nest2.test2");
        }
    }


}
