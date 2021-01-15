package com.sda.main;

import com.sda.calc.Calculator;

public class Main {

    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.divide(5d, 0d));
    }
}
