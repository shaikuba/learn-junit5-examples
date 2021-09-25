package learn.junit5.shopping.basic.naming;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Slf4j
@DisplayName("Some amazing testcase")
public class DisplayExample {

    @Test
    @DisplayName("A passed testcase")
    void test_one(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("A failed testcase")
    void test_two() {
    }

}
