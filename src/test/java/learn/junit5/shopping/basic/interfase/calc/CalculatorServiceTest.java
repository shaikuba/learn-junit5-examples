package learn.junit5.shopping.basic.interfase.calc;

import learn.junit5.shopping.basic.interfase.ServiceTest;
import org.junit.jupiter.api.Test;
import org.junit5.learning.example.calc.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface CalculatorServiceTest extends ServiceTest<CalculatorService> {

    @Test
    default void add() {
        CalculatorService calculatorService = createService();

        assertEquals(3, calculatorService.add(1, 2));
    }

    @Test
    default void subtract() {
        CalculatorService calculatorService = createService();

        assertEquals(-1, calculatorService.subtract(1, 2));
    }

    @Test
    default void multiply() {
        CalculatorService calculatorService = createService();

        assertEquals(2, calculatorService.multiply(1, 2));
    }

    @Test
    default void divide() {
        CalculatorService calculatorService = createService();

        assertEquals(0, calculatorService.divide(1, 2));
    }

}
