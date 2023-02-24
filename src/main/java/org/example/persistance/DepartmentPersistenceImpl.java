package org.example.persistance;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.service.writer.WriteToFile;

import java.util.ArrayList;
import java.util.List;

public class DepartmentPersistenceImpl implements DepartmentPersistence {
    private final List<Department> departments;
    private final WriteToFile writeToOutputFile = new WriteToFile();

    public DepartmentPersistenceImpl() {
        this.departments = new ArrayList<>();
    }

    @Override
    public void addDepartment(Department department) {
        Department foundDepartment = departments.stream()
                .filter(dep -> dep.getID().equals(department.getID()))
                .findFirst()
                .orElse(null);

        if (foundDepartment != null) {
            String message = "Department with " + department.getID() + " already exists!";
            writeToOutputFile.write(message);
            throw new IllegalArgumentException(message);
        }

        departments.add(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return this.departments;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return this.departments.stream()
                .filter(department -> department.getName().equals(departmentName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Department searchInListForDepartmentById(String departmentId) {
        List<Department> departments = this.getAllDepartments();

        Department foundDepartment = departments.stream()
                .filter(dep -> dep.getID().equals(departmentId))
                .findFirst()
                .orElse(null);

        if (foundDepartment == null) {
            String message = "Department with ID " + departmentId + " does not exist!";
            System.out.println(message);
            writeToOutputFile.write(message);
            return null;
        }

        return foundDepartment;
    }

    @Override
    public void updateDepartmentName(Department department, String newName) {
        department.setName(newName);
    }

    @Override
    public void updateDepartmentBudget(Department department, double newBudget) {
        boolean ableToUpdate = ableToUpdate(department, newBudget);

        if (!ableToUpdate) {
            String message = "You cannot cut that much the budget with the number of employees with their salaries";
            System.out.println(message);
            writeToOutputFile.write(message);
            return;
        }

        department.setBudget(newBudget);
    }

    private boolean ableToUpdate(Department department, double newBudget) {
        double neededBudget = department.getEmployees().stream()
                .mapToDouble(Employee::getYearlySalary)
                .sum();

        return !(newBudget - neededBudget < 0);
    }

}
