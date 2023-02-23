package org.example.presentation;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.service.calculate.LeftoverBudget;

import java.util.List;

public abstract class DepartmentPresenter {
    public static void display(Department department) {
        double leftOverBudget = LeftoverBudget.calcByDepartment(department);

        List<String> employees = department.getEmployees().stream()
                .map(Employee::getId).toList();

        System.out.printf("Department: %s,%nID: %s,%nBudget: %.2f$/year,%nNot allocated: %.2f,%nEmployees: ",
                department.getName(), department.getID(), department.getBudget(), leftOverBudget);

        System.out.printf(String.join(",", employees));

        System.out.println();
    }
}
