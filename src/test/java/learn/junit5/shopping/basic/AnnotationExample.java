package learn.junit5.shopping.basic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(value = TestInstance.Lifecycle.PER_METHOD)
public class AnnotationExample {

    public AnnotationExample() {
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
        System.out.println("test1");
    }


}
