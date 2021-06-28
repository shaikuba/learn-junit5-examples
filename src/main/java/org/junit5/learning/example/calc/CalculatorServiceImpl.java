package org.junit5.learning.example.calc;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int add(int a, int b) {
        System.out.println("add()");
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
        System.out.println("============");
    }
}
