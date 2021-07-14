package mockito.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorService;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class C_VerificationTestCase {

    @InjectMocks
    private CalculatorApplication calcApp;

    @Mock
    private CalculatorService service;

    @Test
    void verifyUsage() {

        calcApp.add(1, 1);

        calcApp.subtract(2, 1);
        calcApp.subtract(2, 1);

        calcApp.multiply(1, 2);
        calcApp.multiply(1, 2);
        calcApp.multiply(1, 2);


        //following two verifications work exactly the same - times(1) is used by default
        verify(service).add(1, 1);
        verify(service, times(1)).add(1, 1);

        //exact number of invocations verification
        verify(service, times(2)).subtract(2, 1);
        verify(service, times(3)).multiply(1, 2);

        //verification using never(). never() is an alias to times(0)
        verify(service, never()).divide(anyInt(), anyInt());

        //verification using atLeast()/atMost()
        verify(service, atMostOnce()).add(1, 1);
        verify(service, atLeastOnce()).subtract(2, 1);
        verify(service, atLeast(2)).subtract(2, 1);
        verify(service, atMost(5)).multiply(1, 2);

    }

    @Test
    void verifyNoInteractionsUsage() {

        calcApp.add(1, 1);
        // verify that the mocks were not interacted
        verifyNoInteractions(service);
    }

    @Test
    void verifyNoMoreInteractionsUsage() {

        calcApp.add(1, 1);

        calcApp.subtract(2, 1);

        //following two verifications work exactly the same - times(1) is used by default
        verify(service).add(1, 1);
        verify(service, times(1)).add(1, 1);

        verifyNoMoreInteractions(service);
    }


    @Test
    void verifyInOrder() {

        calcApp.add(1, 1);
        calcApp.subtract(2, 1);

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(service);

        inOrder.verify(service).add(1, 1);
        inOrder.verify(service).subtract(2, 1);
    }

}
