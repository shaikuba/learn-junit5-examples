package learn.junit5.shopping.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@Tag(value = "A")
public class ConcurrentTestExamples_A {


    @Test
    void test_A_first(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
        fail("test failed caused by some reasons.");
    }

    @Test
    void test_A_second(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
    }


    @Test
    void test_A_three(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
    }


}
