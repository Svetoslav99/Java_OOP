package org.example.service.calculate;

import org.example.data.Department;
import org.example.data.Employee;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class LeftoverBudgetTest {

    @Test
    public void testCalcByDepartmentWithEmptyEmployees() {
        Department department = new Department("123", "IT", 100000.0);
        double leftoverBudget = LeftoverBudget.calcByDepartment(department);
        assertEquals(100000.0, leftoverBudget, 0.0);
    }

    @Test
    public void testCalcByDepartmentWithEmployees() {
        Department department = new Department("123", "IT", 100000.0);
        Employee employee1 = new Employee("1", "John", "Doe", 50000.0);
        Employee employee2 = new Employee("2", "Jane", "Doe", 40000.0);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        department.setEmployees(employees);
        double leftoverBudget = LeftoverBudget.calcByDepartment(department);
        assertEquals(10000.0, leftoverBudget, 0.0);
    }

    @Test
    public void testCalcByDepartmentWithNegativeBudget() {
        Department department = new Department("123", "IT", -100000.0);
        Employee employee1 = new Employee("1", "John", "Doe", 50000.0);
        Employee employee2 = new Employee("2", "Jane", "Doe", 40000.0);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        department.setEmployees(employees);
        double leftoverBudget = LeftoverBudget.calcByDepartment(department);
        assertEquals(-190000.0, leftoverBudget, 0.0);
    }
}