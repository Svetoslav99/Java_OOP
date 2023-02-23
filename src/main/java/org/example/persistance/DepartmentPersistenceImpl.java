package org.example.persistance;

import org.example.data.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentPersistenceImpl implements DepartmentPersistence {
    private final List<Department> departments;

    public DepartmentPersistenceImpl() {
        this.departments = new ArrayList<>();
    }

    @Override
    public void addDepartment(Department department) {
        List<Department> foundDepartment = departments.stream()
                .filter(dep -> dep.getID().equals(department.getID()))
                .toList();

        if (foundDepartment.size() != 0) {
            throw new IllegalArgumentException("Department with " + department.getID() + " already exists!");
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
            System.out.println("Department with ID " + departmentId + " does not exist!");
            return null;
        }

        return foundDepartment;
    }

}
