package com.yanchware.fractal.samples.environment.intialization.operational.Utils;

import java.util.Random;

public class TestUtils {
    public static String generate12DigitNumber() {
        var random = new Random();
        // Generate random number between 1 and 999,999,999,999
        int randomNumber = random.nextInt((int) Math.pow(10, 12) - 1) + 1; 
        return String.format("%012d", randomNumber);
    }
}
