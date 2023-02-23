package org.example.persistance;

import org.example.data.Department;
import org.example.data.Employee;

import java.util.List;

public interface EmployeePersistence {
    void hireEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee searchInListForEmployeeById(String employeeId);
}
