package org.example.service.employee;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.persistance.DepartmentPersistence;
import org.example.persistance.DepartmentPersistenceImpl;
import org.example.persistance.EmployeePersistence;
import org.example.persistance.EmployeePersistenceImpl;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromoteEmployeeTest {

    @Test
    void testPromoteEmployee() {
        EmployeePersistence employeePersistence = new EmployeePersistenceImpl();
        DepartmentPersistence departmentPersistence = new DepartmentPersistenceImpl();
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();

        Employee employee = new Employee("John", "Doe", "1001", 50000);
        employee.setDepartmentName("Marketing");
        employeePersistence.hireEmployee(employee);

        Department department = new Department("Marketing", "1001", 60000);
        departmentPersistence.addDepartment(department);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        departmentsWithEmployees.put(department, employeeList);

        PromoteEmployee promoteEmployee = new PromoteEmployee(employeePersistence, departmentPersistence, departmentsWithEmployees);
        promoteEmployee.promote("1001", 10);

        Assertions.assertEquals(55000, employee.getYearlySalary());
    }

    @Test
    void testPromoteNonexistentEmployee() {
        EmployeePersistence employeePersistence = new EmployeePersistenceImpl();
        DepartmentPersistence departmentPersistence = new DepartmentPersistenceImpl();
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();

        PromoteEmployee promoteEmployee = new PromoteEmployee(employeePersistence, departmentPersistence, departmentsWithEmployees);
        promoteEmployee.promote("1001", 10);

        // Since the employee with ID 1001 does not exist, there should be no changes made to the employee list
        Assertions.assertTrue(employeePersistence.getAllEmployees().isEmpty());
    }

    @Test
    void testPromoteInvalidPromotionPercentage() {
        EmployeePersistence employeePersistence = new EmployeePersistenceImpl();
        DepartmentPersistence departmentPersistence = new DepartmentPersistenceImpl();
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();

        PromoteEmployee promoteEmployee = new PromoteEmployee(employeePersistence, departmentPersistence, departmentsWithEmployees);
        promoteEmployee.promote("1001", -10);

        // Since the promotion percentage is negative, there should be no changes made to the employee list
        Assertions.assertTrue(employeePersistence.getAllEmployees().isEmpty());
    }

    @Test
    void testPromoteEmployeeExceedsDepartmentBudget() {
        EmployeePersistence employeePersistence = new EmployeePersistenceImpl();
        DepartmentPersistence departmentPersistence = new DepartmentPersistenceImpl();
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();

        Employee employee = new Employee("John", "Doe", "1001", 50000);
        employee.setDepartmentName("Marketing");
        employeePersistence.hireEmployee(employee);

        Department department = new Department("Marketing", "1001", 55000);
        departmentPersistence.addDepartment(department);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        departmentsWithEmployees.put(department, employeeList);

        PromoteEmployee promoteEmployee = new PromoteEmployee(employeePersistence, departmentPersistence, departmentsWithEmployees);
        promoteEmployee.promote("1001", 20);

        // Since the promotion exceeds the department's budget, there should be no changes made to the employee's salary
        Assertions.assertEquals(50000, employee.getYearlySalary());
    }

}