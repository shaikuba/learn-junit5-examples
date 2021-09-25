package learn.junit5.shopping.basic.assertion;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AssertionsExample {

    @Test
    void assert_line_one() {
        List<String> expected = new ArrayList<>();
        expected.add("hello"); // skip one actual line and compare with first subsequent expect line.
        expected.add(">>2>>");
        expected.add("hey");
        expected.add("hey3");

        List<String> actual = new ArrayList<>();
        actual.add("hello");
        actual.add("hi");
        actual.add("hey1");
        actual.add("hey1");
        actual.add("hey3");


        assertLinesMatch(expected, actual);
    }

    @Test
    void assert_line_two() {
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
    void assert_throw_exception() {
        assertThrows(NullPointerException.class, () -> log.info("{}", 1 / 0));
    }

    @Test
    void assert_all_example() {

        log.info("All assertions would be executed whatever if the first assertion failed or not.");
//        assertAll(
//                () -> assertEquals(2, 1)
//                , () -> assertEquals(3, 1)
//        );

        log.info("assert all executables do not throw exceptions.");
        assertAll("errors, ", Stream.of(() -> {
            throw new OutOfMemoryError();
        }, () -> log.info("No errors")));

//        assertAll("errors, ", Stream.of(
//                () -> {
//                    throw new NullPointerException();
//                }
//                , () -> log.info("No errors")
//        ));
    }

    @Test
    void assert_timeout_example() {
        int i = assertTimeout(Duration.ofMillis(100), () -> {
            Thread.currentThread().sleep(5000);
            log.info("Sleeping finished");
            return 1;
        });

        assertEquals(1, i);
    }

    @Test
    void assert_timeout_preemptive_example() {
        int i = assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.currentThread().sleep(5000);
            log.info("Sleeping finished");
            return 1;
        });

        assertEquals(1, i);
    }

}
