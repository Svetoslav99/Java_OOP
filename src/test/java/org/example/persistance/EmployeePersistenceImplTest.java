package org.example.persistance;

import org.example.data.Employee;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeePersistenceImplTest {

    @Test
    public void testHireEmployee() {
        EmployeePersistenceImpl employeePersistence = new EmployeePersistenceImpl();

        Employee employee1 = new Employee("001", "John", "Doe", 50000.0);
        Employee employee2 = new Employee("002", "Jane", "Doe", 60000.0);

        // Hiring a new employee should add them to the list
        employeePersistence.hireEmployee(employee1);
        assertEquals(1, employeePersistence.getAllEmployees().size());
        assertTrue(employeePersistence.getAllEmployees().contains(employee1));

        // Hiring an employee with the same ID should throw an exception
        assertThrows(IllegalArgumentException.class, () -> employeePersistence.hireEmployee(employee1));

        // Hiring a different employee should add them to the list as well
        employeePersistence.hireEmployee(employee2);
        assertEquals(2, employeePersistence.getAllEmployees().size());
        assertTrue(employeePersistence.getAllEmployees().contains(employee2));
    }

    @Test
    public void testGetAllEmployees() {
        EmployeePersistenceImpl employeePersistence = new EmployeePersistenceImpl();

        Employee employee1 = new Employee("001", "John", "Doe", 50000.0);
        Employee employee2 = new Employee("002", "Jane", "Doe", 60000.0);

        // The list of employees should initially be empty
        assertEquals(0, employeePersistence.getAllEmployees().size());

        // Hiring employees should add them to the list
        employeePersistence.hireEmployee(employee1);
        employeePersistence.hireEmployee(employee2);
        assertEquals(2, employeePersistence.getAllEmployees().size());
        assertTrue(employeePersistence.getAllEmployees().contains(employee1));
        assertTrue(employeePersistence.getAllEmployees().contains(employee2));
    }

    @Test
    public void testSearchInListForEmployeeById() {
        EmployeePersistenceImpl employeePersistence = new EmployeePersistenceImpl();

        Employee employee1 = new Employee("001", "John", "Doe", 50000.0);
        Employee employee2 = new Employee("002", "Jane", "Doe", 60000.0);

        // Searching for an employee that doesn't exist should return null
        assertNull(employeePersistence.searchInListForEmployeeById("003"));

        // Searching for an employee that has been hired should return that employee
        employeePersistence.hireEmployee(employee1);
        employeePersistence.hireEmployee(employee2);
        assertEquals(employee1, employeePersistence.searchInListForEmployeeById("001"));
        assertEquals(employee2, employeePersistence.searchInListForEmployeeById("002"));
    }

    @Test
    public void searchInListForEmployeeById_shouldReturnEmployeeWithMatchingId() {
        EmployeePersistenceImpl employeePersistence = new EmployeePersistenceImpl();

        Employee employee1 = new Employee("001", "John", "Doe", 50000.0);
        employeePersistence.hireEmployee(employee1);

        Employee foundEmployee = employeePersistence.searchInListForEmployeeById("001");
        assertEquals("John", foundEmployee.getFirstName());
        assertEquals("Doe", foundEmployee.getLastName());
        assertEquals(50000, foundEmployee.getYearlySalary(), 0.001);

    }

    @Test
    public void searchInListForEmployeeById_shouldReturnNullForNonexistentId() {
        EmployeePersistenceImpl employeePersistence = new EmployeePersistenceImpl();

        Employee foundEmployee = employeePersistence.searchInListForEmployeeById("3");
        assertNull(foundEmployee);
    }


}