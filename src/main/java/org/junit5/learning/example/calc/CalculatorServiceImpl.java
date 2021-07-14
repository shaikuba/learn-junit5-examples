package org.junit5.learning.example.calc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public void printAdd(int a, int b) {
        System.out.println(add(a, b));
    }

    @Override
    public void print() {
        log.info("The print method of CalculatorServiceImpl for printing message on console.");
    }
}
