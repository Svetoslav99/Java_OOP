package org.example;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.persistance.*;
import org.example.presentation.DepartmentPresenter;
import org.example.presentation.EmployeePresenter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        DepartmentPersistence departments = new DepartmentPersistenceImpl();
        EmployeePersistence employees = new EmployeePersistenceImpl();

        DepartmentWithEmployeesPersistence departmentsWithEmployees = new DepartmentWithEmployeesImpl(departments, employees);

        while (!input.equals("End")) {
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "CreateDepartment" -> {
                    String departmentId = tokens[1];
                    String name = tokens[2];
                    double budget = Double.parseDouble(tokens[3]);

                    Department department = new Department(departmentId, name, budget);
                    departments.addDepartment(department);
                }
                case "HireEmployee" -> {
                    String employeeId = tokens[1];
                    String firstName = tokens[2];
                    String lastName = tokens[3];
                    double yearlySalary = Double.parseDouble(tokens[4]);

                    Employee employee = new Employee(employeeId, firstName, lastName, yearlySalary);
                    employees.hireEmployee(employee);
                }
                case "AssignDepartment" -> {
                    String employeeId = tokens[1];
                    String departmentId = tokens[2];

                    departmentsWithEmployees.assignDepartmentToEmployee(departmentId, employeeId);
                }
                case "PromoteEmployee" -> {
                    String employeeId = tokens[1];
                    double promotionPercentage = Double.parseDouble(tokens[2]);

                    departmentsWithEmployees.promoteEmployee(employeeId, promotionPercentage);
                }
                case "ShowEmployee" -> {
                    String employeeId = tokens[1];

                    Employee foundEmployee = employees.searchInListForEmployeeById(employeeId);

                    if (foundEmployee != null)
                        EmployeePresenter.display(foundEmployee);
                }

                case "ShowDepartment" -> {
                    String departmentId = tokens[1];

                    Department foundDepartment = departments.searchInListForDepartmentById(departmentId);

                    if (foundDepartment != null)
                        DepartmentPresenter.display(foundDepartment);

                }

            }

            input = scanner.nextLine();
        }
    }
}