package learn.junit5.shopping.basic.naming;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@IndicativeSentencesGeneration(separator = ", ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("A amazing test class")
public class IndicativeSentencesGenerationExample {

    @DisplayName("A amazing test one")
    @Test
    void test_one(TestInfo testInfo) {
        log.info(testInfo.getTestClass().get().getName());
        log.info(testInfo.getDisplayName());
    }

    @Test
    void test_two() {
    }

    @Test
    void test_three() {
    }

}
