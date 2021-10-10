package learn.junit5.shopping.repeated;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.junit5.learning.example.calc.CalculatorService;
import org.junit5.learning.example.calc.CalculatorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME;
import static org.junit.jupiter.api.RepeatedTest.SHORT_DISPLAY_NAME;

@Slf4j
public class CalculatorRepeatedExample {

    private CalculatorService calculatorService;

    @BeforeEach
    void init_calc_service() {
        calculatorService = new CalculatorServiceImpl();
    }

    @RepeatedTest(5)
    void add(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
        assertEquals(3, calculatorService.add(1, 2));
    }

    @RepeatedTest(value = 5, name = SHORT_DISPLAY_NAME)
    void subtract() {
        assertEquals(-1, calculatorService.subtract(1, 2));
    }

    @RepeatedTest(value = 5, name = LONG_DISPLAY_NAME)
    void multiply() {
        assertEquals(2, calculatorService.multiply(1, 2));
    }

    @RepeatedTest(value = 5, name = "{displayName} - {currentRepetition}/{totalRepetitions}")
    void divide(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
        assertEquals(1, calculatorService.divide(2, 2));
    }
}
