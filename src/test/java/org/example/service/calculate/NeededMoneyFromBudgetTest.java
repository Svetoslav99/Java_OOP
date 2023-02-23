package org.example.service.calculate;


import org.example.data.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeededMoneyFromBudgetTest {

    @Test
    public void testCalcByEmployeesAndIncomingEmployeeWithNullEmployees() {
        Employee incomingEmployee = new Employee("1", "John", "Doe", 50000.0);
        double expectedNeededMoney = 50000.0;

        double actualNeededMoney = NeededMoneyFromBudget.calcByEmployeesAndIncomingEmployee(null, incomingEmployee);

        assertEquals(expectedNeededMoney, actualNeededMoney);
    }

    @Test
    public void testCalcByEmployeesAndIncomingEmployeeWithNoExistingEmployees() {
        Employee incomingEmployee = new Employee("1", "John", "Doe", 50000.0);
        List<Employee> employees = new ArrayList<>();
        double expectedNeededMoney = 50000.0;

        double actualNeededMoney = NeededMoneyFromBudget.calcByEmployeesAndIncomingEmployee(employees, incomingEmployee);

        assertEquals(expectedNeededMoney, actualNeededMoney);
    }

    @Test
    public void testCalcByEmployeesAndIncomingEmployeeWithExistingEmployees() {
        Employee incomingEmployee = new Employee("1", "John", "Doe", 50000.0);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("2", "Jane", "Doe", 60000.0));
        employees.add(new Employee("3", "Bob", "Smith", 70000.0));
        double expectedNeededMoney = 180000.0;

        double actualNeededMoney = NeededMoneyFromBudget.calcByEmployeesAndIncomingEmployee(employees, incomingEmployee);

        assertEquals(expectedNeededMoney, actualNeededMoney);
    }
}