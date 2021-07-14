package mockito.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class A_MockCodeTestCase {

    private CalculatorService service;

    private CalculatorApplication calcApp;

    @BeforeEach
    void initMocks() {
        service = mock(CalculatorService.class);
        calcApp = new CalculatorApplication(service);
    }

    @Test
    void mockTest() {
        assertEquals(0, calcApp.add(1, 1));

        verify(service, times(2)).add(anyInt(), anyInt());
    }

}
