package org.example.service.department;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.persistance.DepartmentPersistence;
import org.example.persistance.EmployeePersistence;
import org.example.service.calculate.NeededMoneyFromBudget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssignDepartmentToEmployee {
    private DepartmentPersistence departmentPersistence;
    private Map<Department, List<Employee>> departmentsWithEmployees;
    private EmployeePersistence employeePersistence;

    public AssignDepartmentToEmployee(DepartmentPersistence departmentPersistence, Map<Department,
            List<Employee>> departmentsWithEmployees, EmployeePersistence employeePersistence) {
        this.departmentPersistence = departmentPersistence;
        this.departmentsWithEmployees = departmentsWithEmployees;
        this.employeePersistence = employeePersistence;
    }

    public void assign(String departmentId, String employeeId) {
        Department department = departmentPersistence.searchInListForDepartmentById(departmentId);

        if (department == null)
            return;

        Employee employee = employeePersistence.searchInListForEmployeeById(employeeId);

        if (employee == null)
            return;

        List<Employee> employeeList = this.departmentsWithEmployees.get(department);

        double neededMoneyForDepartment = NeededMoneyFromBudget.calcByEmployeesAndIncomingEmployee(employeeList, employee);

        if (neededMoneyForDepartment > department.getBudget()) {
            System.out.println("Unable to add employee " + employeeId + " to department " + departmentId + " as there is not enough budget!");
            return;
        }

        employee.setDepartmentName(department.getName());

        if (employeeList == null) {
            List<Employee> newEmployeeList = new ArrayList<>();
            newEmployeeList.add(employee);
            this.departmentsWithEmployees.put(department, newEmployeeList);
            department.setEmployees(newEmployeeList);
        } else {
            employeeList.add(employee);
            this.departmentsWithEmployees.put(department, employeeList);

            department.setEmployees(employeeList);
        }
    }
}
