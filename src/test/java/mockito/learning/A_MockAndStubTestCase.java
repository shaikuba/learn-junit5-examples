package mockito.learning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class A_MockAndStubTestCase {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorApplication calcApp;

    @Test
    void mockTest() {

        calculatorService.add(anyInt(), anyInt());
        when(0).thenReturn(3);
        //when(calculatorService.add(anyInt(), anyInt())).thenReturn(3);

        when(calculatorService.subtract(anyInt(), anyInt())).thenReturn(2);


//        throw NPE because of primitive types arguments can only using primitive argument matchers
//        when(calculatorService.add(any(),any())).thenReturn(2);

        assertAll(() -> assertEquals(3, calcApp.add(2, 3)), () -> assertEquals(2, calcApp.subtract(2, 5)));

        verify(calculatorService, times(1)).add(anyInt(), anyInt());
    }

    @Test
    void doReturnTest() {

        doReturn(2).when(calculatorService).add(anyInt(), anyInt());

        assertEquals(calcApp.add(1, 2), 2);

        verify(calculatorService, times(1)).add(anyInt(), anyInt());
    }

    @Test
    void doThrowTest() {

        // stubbing void method can be completed only using doSomething family method.
        doThrow(NullPointerException.class).when(calculatorService).print();

        calcApp.print();

        verify(calculatorService, times(1)).print();
    }

    @Test
    void consecutiveCallStubbing() {
        when(calculatorService.add(anyInt(), anyInt()))
                .thenReturn(1)
                .thenReturn(2);

        assertEquals(1, calcApp.add(1, 1));
        assertEquals(2, calcApp.add(1, 1));

        // shorter version of consecutive stubbing, this would be override previous one
        when(calculatorService.add(anyInt(), anyInt()))
                .thenReturn(1, 2);

        assertEquals(1, calcApp.add(1, 1));
        assertEquals(2, calcApp.add(1, 1));

    }

    @Test
    void stubbingWithCallback() {
        when(calculatorService.add(anyInt(), anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return (int) args[0] + (int) args[1];
        });

        assertEquals(3, calcApp.add(2, 1));
    }
}
