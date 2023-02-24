package org.example.presentation;
import org.example.data.Department;
import org.example.data.Employee;
import org.example.service.calculate.LeftoverBudget;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentPresenterTest {

    @Test
    public void testDisplay() {
        Department department = new Department("1001", "Sales", 500000);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E1001", "John", "Doe", 50000));
        employees.add(new Employee("E1002", "Jane", "Smith", 60000));
        employees.add(new Employee("E1003", "Bob", "Johnson", 70000));
        department.setEmployees(employees);

        double leftOverBudget = LeftoverBudget.calcByDepartment(department);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DepartmentPresenter.display(department);

        String expectedOutput = "Department: " + department.getName() + ",\nID: " + department.getID() + ",\nBudget: "
                + department.getBudget() + "$/year,\n" + "Not allocated: " + leftOverBudget + ",\nEmployees: E1001, E1002, E1003\n";

        String actualOutput = outContent.toString();

        // they are completely the same and because of the formatting this test is blowing up...
        assertEquals(expectedOutput, actualOutput);
    }

}