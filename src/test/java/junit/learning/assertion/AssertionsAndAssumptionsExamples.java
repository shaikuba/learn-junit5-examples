package junit.learning.assertion;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit5.learning.example.calc.CalculatorApplication;
import org.junit5.learning.example.calc.CalculatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AssertionsAndAssumptionsExamples {

    private CalculatorApplication calc = new CalculatorApplication(new CalculatorServiceImpl());



    @Test
    void assertionExample() {
        int addRes = calc.add(1, 2);
        //assertEquals(1, 3, () -> "test failed");

        List<String> list1 = new ArrayList<>();
        list1.add("venus");
        list1.add(">>2>>");
        list1.add("venus1");
        list1.add("venus2");
        list1.add("venus3");

        List<String> list2 = new ArrayList<>();
        list2.add("venus");
        list2.add("jupi");
        list2.add("venus5");
        list2.add("venus1");
        list2.add("venus2");
        list2.add("venus3");


        Assertions.assertLinesMatch(list1, list2);


//        Assertions.assertAll(()-> log.info("add res: {}", calc.add(1, 2)),
//                 ()-> log.info("substrac res: {}",calc.divide(3, 0)));

        Assertions.assertThrows(ArithmeticException.class, ()-> log.info("substrac res: {}",calc.divide(3, 0)));
    }
}
