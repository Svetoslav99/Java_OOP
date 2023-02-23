package org.example.presentation;


import org.example.data.Employee;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class EmployeePresenterTest {

    @Test
    public void testDisplay() {
        Employee employee = new Employee("1234", "John", "Doe", 50000.0);
        employee.setDepartmentName("Sales");
        String expectedOutput = String.format("Employee ID: 1234, Name: John Doe, Department: Sales, Salary: 50000.00%n");
        assertEquals(expectedOutput, getStandardOutputFromMethod(() -> EmployeePresenter.display(employee)));
    }

    // Utility method to capture the standard output from a method
    private String getStandardOutputFromMethod(Runnable method) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            method.run();
            System.out.flush();
            return baos.toString();
        } finally {
            System.setOut(old);
        }
    }
}