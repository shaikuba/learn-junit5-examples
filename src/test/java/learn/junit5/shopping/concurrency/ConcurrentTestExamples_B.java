package learn.junit5.shopping.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Slf4j
@Tag(value = "B")
public class ConcurrentTestExamples_B {
    
    
    @Test
    void test_B_first(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
    }

    @Test
    void test_B_second(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
    }


    @Test
    void test_B_three(TestInfo testInfo) {
        log.info("Thread id: {}, test name: {}", Thread.currentThread().getId() , testInfo.getDisplayName());
    }


}
