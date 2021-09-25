package learn.junit5.shopping.basic.interfase;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifecycleLogger {

    Logger logger = LoggerFactory.getLogger(TestLifecycleLogger.class);

    @BeforeAll
    default void before_all_tests() {
        logger.info("Before all tests");
    }

    @AfterAll
    default void after_all_tests() {
        logger.info("After all tests");
    }

    @BeforeEach
    default void before_test(TestInfo testInfo) {
        logger.info("About to execute [{}]", testInfo.getDisplayName());
    }

    @AfterEach
    default void after_test(TestInfo testInfo) {
        logger.info("Finished executing [{}]", testInfo.getDisplayName());
    }

}
