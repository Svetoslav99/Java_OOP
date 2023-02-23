package org.example.persistance;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.service.department.AssignDepartmentToEmployee;
import org.example.service.employee.PromoteEmployee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentWithEmployeesImpl implements DepartmentWithEmployeesPersistence {
    private DepartmentPersistence departmentPersistence;
    private EmployeePersistence employeePersistence;
    private Map<Department, List<Employee>> departmentsWithEmployees;
    private PromoteEmployee promoteEmployee;
    private AssignDepartmentToEmployee assignDepartmentToEmployee;


    public DepartmentWithEmployeesImpl(DepartmentPersistence departmentPersistence,
                                       EmployeePersistence employeePersistence) {
        this.departmentPersistence = departmentPersistence;
        this.employeePersistence = employeePersistence;
        this.departmentsWithEmployees = new HashMap<>();
    }

    @Override
    public void assignDepartmentToEmployee(String departmentId, String employeeId) {
        this.assignDepartmentToEmployee = new AssignDepartmentToEmployee(departmentPersistence, departmentsWithEmployees, employeePersistence);
        this.assignDepartmentToEmployee.assign(departmentId, employeeId);
    }

    @Override
    public void promoteEmployee(String employeeId, double promotionPercentage) {
        this.promoteEmployee = new PromoteEmployee(employeePersistence, departmentPersistence, departmentsWithEmployees);
        this.promoteEmployee.promote(employeeId, promotionPercentage);
    }

}
