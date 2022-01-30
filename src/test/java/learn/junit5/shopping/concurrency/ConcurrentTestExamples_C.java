package learn.junit5.shopping.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Slf4j
@Tag(value = "C")
public class ConcurrentTestExamples_C {


    @Test
    void test_C_first(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId(), testInfo.getDisplayName());
    }

    @Test
    void test_C_second(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId(), testInfo.getDisplayName());
    }

    @Test
    void test_C_three(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId(), testInfo.getDisplayName());
    }

}
