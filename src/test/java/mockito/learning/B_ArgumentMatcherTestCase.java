package mockito.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class B_ArgumentMatcherTestCase {

    @Mock
    private CalculatorService service;

    @InjectMocks
    private CalculatorApplication calcApp;

    @Test
    void argMatcher1() {
        when(service.add(0, 0)).thenReturn(1);
        //when(service.add(1, 0)).thenReturn(1);  // argument mismatch

        assertEquals(1, calcApp.add(0, 0));

    }

    @Test
    void argMatcher2() {
        when(service.add(anyInt(), anyInt())).thenReturn(1);
        //when(service.add(1, 0)).thenReturn(1);  // argument mismatch

        assertEquals(1, calcApp.add(0, 0));

    }

    @Test
    void argMatcher3() {
        when(service.add(intThat((arg) -> arg.equals(0)), anyInt())).thenReturn(1);
        //when(service.add(1, 0)).thenReturn(1);  // argument mismatch

        assertEquals(1, calcApp.add(0, 0));

    }

}
