package learn.junit5.shopping.injection;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

/**
 * DependencyInjectionExample
 */
@Slf4j
public class DependencyInjectionExample {

    @Nested
    class TestInfoParameterResolverExample {

        TestInfoParameterResolverExample(TestInfo testInfo) {
            log.info("DisplayName: {}", testInfo.getDisplayName());
        }

        @BeforeEach
        void init(TestInfo testInfo) {
            log.info("TestClass: {}", testInfo.getTestClass().get().getName());
        }

        @Tag("testInfo")
        @Test
        void print_test_info(TestInfo testInfo) {
            log.info("TestMethod: {}", testInfo.getTestMethod().get().getName());
            log.info("Test Tags: {}", Joiner.on(",").skipNulls().join(testInfo.getTags()));
        }
    }

    @Nested
    class TestReporterParameterResolverExample {

        TestReporterParameterResolverExample(TestReporter testReporter) {
            testReporter.publishEntry("some extra message on report");
        }

        @BeforeEach
        void init(TestReporter testReporter) {
            testReporter.publishEntry("Test Reporter Intro", "some extra message on report");
        }

        @Test
        void publish_test_report_info(TestReporter testReporter) {
            Map<String, String> values = new HashMap<>();
            values.put("tester", "ray");
            values.put("date", "20300101");
            testReporter.publishEntry(values);
        }
    }

}
