package org.example.service.calculate;

public abstract class NewSalary {
    public static double calc(double currentSalary, double promotePercentage) {
        return currentSalary * (1 + promotePercentage / 100);
    }
}
