package com.test.fitnesse.logic;

import fit.ColumnFixture;

/**
 * Created by renuka.prasad on 4/5/2016.
 */
public class CalculatorFixture extends ColumnFixture {

    public int first;

    public int second;

    public int product() {
        return first * second;
    }

    public int sum() {
        return first + second;
    }
}
