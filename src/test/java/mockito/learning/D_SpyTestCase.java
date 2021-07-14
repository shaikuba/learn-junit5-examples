package mockito.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;
import org.junit5.learning.example.calc.CalculatorServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class D_SpyTestCase {

    @Spy
    private CalculatorServiceImpl calculatorService;

    @InjectMocks
    private CalculatorApplication calcApp;

    @Test
    void spyMethodTest() {

        CalculatorService spyCalService = spy(CalculatorServiceImpl.class);

        when(spyCalService.add(anyInt(), anyInt())).thenReturn(2);
        assertEquals(2, spyCalService.add(1, 1));
        verify(spyCalService, times(1)).add(anyInt(), anyInt());

    }

    @Test
    void spyRealMethod() {
        when(calculatorService.add(anyInt(), anyInt())).thenReturn(3);
        assertEquals(3, calcApp.add(1, 1));
        verify(calculatorService, times(1)).add(anyInt(), anyInt());

//        assertEquals(0, calculatorService.subtract(1, 1));
//        verify(calculatorService, times(1)).subtract(anyInt(), anyInt());
    }

    @Spy
    private List<Integer> integers = new ArrayList<>();
    @Test
    void spyArrayList() {

        // throw IndexOutOfBoundsException
//        when(integers.get(0)).thenReturn(1);

        doReturn(1).when(integers).get(anyInt());
        assertEquals(1, integers.get(1));
    }
}
