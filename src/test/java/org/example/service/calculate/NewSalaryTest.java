package org.example.service.calculate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewSalaryTest {

    @Test
    public void testCalcWithZeroPromotePercentage() {
        double currentSalary = 50000;
        double promotePercentage = 0;

        double newSalary = NewSalary.calc(currentSalary, promotePercentage);

        assertEquals(currentSalary, newSalary, 0.01);
    }

    @Test
    public void testCalcWithPositivePromotePercentage() {
        double currentSalary = 50000;
        double promotePercentage = 10;

        double newSalary = NewSalary.calc(currentSalary, promotePercentage);

        assertEquals(55000, newSalary, 0.01);
    }

    @Test
    public void testCalcWithNegativePromotePercentage() {
        double currentSalary = 50000;
        double promotePercentage = -5;

        double newSalary = NewSalary.calc(currentSalary, promotePercentage);

        assertEquals(47500, newSalary, 0.01);
    }
}