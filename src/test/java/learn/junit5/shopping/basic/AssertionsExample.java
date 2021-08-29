package learn.junit5.shopping.basic;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsExample {

    @Test
    void assertLinesMatchExample() {
        List<String> expected = new ArrayList<>();
        expected.add(">>1>>"); // skip one actual line and compare with first subsequent expect line.
        expected.add(">>3>>");
        expected.add("hey");
        expected.add("hey3");

        List<String> actual = new ArrayList<>();
        actual.add("hello");
        actual.add("hi");
        actual.add("hey1");
        actual.add("hey1");
        actual.add("hey");
        actual.add("hey3");


        assertLinesMatch(expected, actual);
    }

    @Test
    void assertLinesMatchSkip() {
        List<String> expected = new ArrayList<>();
        expected.add("hello"); // skip one actual line and compare with first subsequent expect line.
        expected.add(">>2>>");
        expected.add("hey");
        expected.add("hey3");

        List<String> actual = new ArrayList<>();
        actual.add("hello");
        actual.add("hi");
        actual.add("hey1");
        actual.add("hey");
        actual.add("hey3");


        assertLinesMatch(expected, actual);
    }

    @Test
    void assertAllTest() {

        System.out.println("All assertions would be executed whatever if the first assertion failed or not.");
//        assertAll(
//                () -> assertEquals(2, 1)
//                , () -> assertEquals(3, 1)
//        );

        System.out.println("assert all executables do not throw exceptions.");
        assertAll("errors, ", Stream.of(
                () -> {
                    throw new OutOfMemoryError();
                }
                , () -> System.out.println("No errors")
        ));

//        assertAll("errors, ", Stream.of(
//                () -> {
//                    throw new NullPointerException();
//                }
//                , () -> System.out.println("No errors")
//        ));
    }

    @Test
    void assertTimeoutTest() {
        int i = assertTimeout(Duration.ofMillis(1), () -> {
            Thread.currentThread().sleep(5000);
            return 1;
        });

        assertEquals(1, i);
    }

    @Test
    void assertTimeoutPreemptivelyTest() {
        int i = assertTimeoutPreemptively(Duration.ofMillis(1), () -> {
            Thread.currentThread().sleep(5000);
            return 1;
        });

        assertEquals(1, i);
    }


}
