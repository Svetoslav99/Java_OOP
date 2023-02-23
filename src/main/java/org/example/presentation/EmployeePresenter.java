package org.example.presentation;

import org.example.data.Department;
import org.example.data.Employee;

public abstract class EmployeePresenter {
    public static void display(Employee employee) {
        System.out.printf("Employee ID: %s, Name: %s %s, Department: %s, Salary: %.2f%n",
                employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentName(), employee.getYearlySalary());
    }

}
