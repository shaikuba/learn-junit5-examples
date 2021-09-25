package learn.junit5.shopping.basic.interfase.calc;

import org.junit5.learning.example.calc.CalculatorService;
import org.junit5.learning.example.calc.CalculatorServiceImpl;

public class CalculatorServiceATest implements CalculatorServiceTest {
    @Override
    public CalculatorService createService() {
        return new CalculatorServiceImpl();
    }
}
