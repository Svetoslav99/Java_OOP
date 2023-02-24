package org.example.presentation;

import org.example.data.Employee;
import org.example.service.writer.WriteToFile;

public abstract class EmployeePresenter {
    public static void display(Employee employee) {
        String message = "Employee ID: " + employee.getId() + ", Name: " + employee.getFirstName() + " " + employee.getLastName()
                + ", Department: " + employee.getDepartmentName() + ", Salary: " + employee.getYearlySalary() + "\n";

        System.out.printf(message);

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.write(message);
    }

}
