package org.example.persistance;

import org.example.data.Employee;
import org.example.service.writer.WriteToFile;

import java.util.ArrayList;
import java.util.List;

public class EmployeePersistenceImpl implements EmployeePersistence {
    private final List<Employee> employees;
    private final WriteToFile writeToOutputFile = new WriteToFile();

    public EmployeePersistenceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void hireEmployee(Employee employee) {
        Employee foundEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(employee.getId()))
                .findFirst()
                .orElse(null);

        if (foundEmployee != null) {
            String message = "Employee with " + employee.getId() + " already exists!";
            writeToOutputFile.write(message);
            System.out.println(message);
            return;
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
            String message = "Employee with ID " + employeeId + " does not exist!";
            writeToOutputFile.write(message);
            System.out.println(message);
            return null;
        }

        return foundEmployee;
    }

}
