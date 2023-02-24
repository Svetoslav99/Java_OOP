package org.example.presentation;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.service.calculate.LeftoverBudget;
import org.example.service.writer.WriteToFile;

import java.util.List;

public abstract class DepartmentPresenter {

    public static void display(Department department) {
        double leftOverBudget = LeftoverBudget.calcByDepartment(department);

        String message = "Department: " + department.getName() + ",\nID: " + department.getID() + ",\nBudget: "
                + department.getBudget() + "$/year,\n" + "Not allocated: " + leftOverBudget + ",\nEmployees: ";

        List<String> employees = department.getEmployees().stream()
                .map(Employee::getId).toList();

        System.out.printf(message);
        System.out.printf(String.join(", ", employees));

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.write(message + String.join(", ", employees));

        System.out.println();
    }
}
