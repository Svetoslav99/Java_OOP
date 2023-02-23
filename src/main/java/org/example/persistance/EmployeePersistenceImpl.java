package org.example.persistance;

import org.example.data.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeePersistenceImpl implements EmployeePersistence{
    private final List<Employee> employees;

    public EmployeePersistenceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void hireEmployee(Employee employee) {
        List<Employee> foundEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employee.getId()))
                .toList();

        if (foundEmployee.size() != 0) {
            throw new IllegalArgumentException("Employee with " + employee.getId() + " already exists!");
        }

        employees.add(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employees;
    }

    @Override
    public Employee searchInListForEmployeeById(String employeeId) {
        List<Employee> employees = this.getAllEmployees();

        Employee foundEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

        if (foundEmployee == null) {
            System.out.println("Employee with ID " + employeeId + " does not exist!");
            return null;
        }

        return foundEmployee;
    }

}
