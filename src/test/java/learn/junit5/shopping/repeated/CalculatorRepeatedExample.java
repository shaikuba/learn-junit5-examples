package learn.junit5.shopping.repeated;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit5.learning.example.calc.CalculatorService;
import org.junit5.learning.example.calc.CalculatorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.RepeatedTest.LONG_DISPLAY_NAME;
import static org.junit.jupiter.api.RepeatedTest.SHORT_DISPLAY_NAME;

public class CalculatorRepeatedExample {

    private CalculatorService calculatorService;

    @BeforeEach
    void init_calc_service() {
        calculatorService = new CalculatorServiceImpl();
    }

    @RepeatedTest(5)
    void add() {
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

    @RepeatedTest(value = 5, name = "")
    void divide() {
        assertEquals(1, calculatorService.divide(2, 2));
    }
}
