package org.junit5.learning.example.calc;

import java.util.Random;

public class CalculatorApplication implements CalculatorService {

    private CalculatorService calculatorService;


    public CalculatorApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public int add(int a, int b) {
        return calculatorService.add(a, b);
    }

    @Override
    public int subtract(int a, int b) {
        return calculatorService.subtract(a, b);
    }

    @Override
    public int multiply(int a, int b) {
        return calculatorService.multiply(a, b);
    }

    @Override
    public int divide(int a, int b) {
        return calculatorService.divide(a, b);
    }

    @Override
    public void printAdd(int a, int b) {
        System.out.println(add(a, b));
    }

    @Override
    public void print() {
        calculatorService.print();
    }

    public static int random() {
        return new Random().nextInt();
    }
}
