package org.example.persistance;

import org.example.data.Department;
import org.example.data.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentWithEmployeesPersistence {
    void assignDepartmentToEmployee(String departmentId, String employeeId);

    void promoteEmployee(String employeeId, double promotionPercentage);
}
