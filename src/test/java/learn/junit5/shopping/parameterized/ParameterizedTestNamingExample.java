package learn.junit5.shopping.parameterized;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_WITH_NAMES_PLACEHOLDER;

@Slf4j
public class ParameterizedTestNamingExample {


    @ParameterizedTest(name = ARGUMENTS_WITH_NAMES_PLACEHOLDER)
    @CsvSource({"apple, 1", "banana, 2", "'lemon, lime', 3"})
    void testWithArguments(String fruit, int rank, TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "test iteration index: {index}, and test parameters as: {arguments}")
    @CsvSource({"apple, 1", "banana, 2", "'lemon, lime', 3"})
    void testWithNamedArguments(String fruit, int rank, TestInfo testInfo) {

        // fruit=apple rank=1, fruit=banana rank=2
        log.info(testInfo.getDisplayName());
    }


}
