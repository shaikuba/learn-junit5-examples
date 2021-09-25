package learn.junit5.shopping.nested;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;
import org.junit5.learning.example.calc.CalculatorServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CalculatorApplicationExample
 */
@Slf4j
public class CalculatorApplicationExample {

    private CalculatorApplication calcApp;

    @BeforeEach
    void initCalc() {
        calcApp = new CalculatorApplication(new CalculatorServiceImpl());
        log.info("Initialize all test dependencies include calcApp and calcServ in before each method.");
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class AddTestScenario {

        @BeforeAll
        void before_all() {
            log.info("before all");
        }

        @Test
        void add_success_positive_number() {
            assertEquals(3, calcApp.add(1, 2));
        }

        @Test
        void add_success_negative_number() {
            assertEquals(-1, calcApp.add(1, -2));
        }

    }

    @Nested
    class SubtractTestScenario {

        @Test
        void subtract_success_positive_number() {
            assertEquals(3, calcApp.subtract(5, 2));
        }

        @Test
        void subtract_success_negative_number() {
            assertEquals(3, calcApp.subtract(1, -2));
        }

    }

    @Nested
    class MultiplyTestScenario {

        @Test
        void multiply_success_positive_number() {
            assertEquals(10, calcApp.multiply(5, 2));
        }

        @Test
        void multiply_success_negative_number() {
            assertEquals(-2, calcApp.multiply(1, -2));
        }

    }

    @Nested
    class DivideTestScenario {

        @Test
        void divide_success_positive_number() {
            assertEquals(2, calcApp.divide(5, 2));
        }

        @Test
        void divide_success_negative_number() {
            assertEquals(0, calcApp.divide(1, -2));
        }

        @Test
        void divide_failed_by_zero() {
            assertThrows(ArithmeticException.class, () -> calcApp.divide(1, 0));
        }

    }
}
