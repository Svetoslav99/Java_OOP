package org.example.service.calculate;

import org.example.data.Department;
import org.example.data.Employee;

public abstract class LeftoverBudget {
    public static double calcByDepartment(Department department) {
        return department.getBudget() - department.getEmployees().stream()
                .mapToDouble(Employee::getYearlySalary)
                .sum();
    }

}
