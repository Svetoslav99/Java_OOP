package org.example.persistance;

import org.example.data.Department;

import java.util.List;

public interface DepartmentPersistence {
    void addDepartment(Department department);
    List<Department> getAllDepartments();

    Department getDepartmentByName(String departmentName);
    Department searchInListForDepartmentById(String departmentId);

    void updateDepartmentName(Department department, String newName);
    void updateDepartmentBudget(Department department, double newBudget);
}
