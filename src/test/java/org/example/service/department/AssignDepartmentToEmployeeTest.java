package org.example.service.department;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.persistance.DepartmentPersistence;
import org.example.persistance.EmployeePersistence;
import org.example.service.calculate.NeededMoneyFromBudget;
import org.example.service.department.AssignDepartmentToEmployee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AssignDepartmentToEmployeeTest {

//    private DepartmentPersistence departmentPersistence;
//    private Map<Department, List<Employee>> departmentsWithEmployees;
//    private EmployeePersistence employeePersistence;
//    private AssignDepartmentToEmployee assignDepartmentToEmployee;

//    @Before
//    public void setUp() {
//        departmentPersistence = mock(DepartmentPersistence.class);
//        departmentsWithEmployees = new HashMap<>();
//        employeePersistence = mock(EmployeePersistence.class);
//        assignDepartmentToEmployee = new AssignDepartmentToEmployee(departmentPersistence, departmentsWithEmployees, employeePersistence);
//    }

    @Test
    public void testAssignEmployeeToDepartmentSuccessfully() {
        DepartmentPersistence departmentPersistence = mock(DepartmentPersistence.class);
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();
        EmployeePersistence employeePersistence = mock(EmployeePersistence.class);
        AssignDepartmentToEmployee assignDepartmentToEmployee = new AssignDepartmentToEmployee(departmentPersistence, departmentsWithEmployees, employeePersistence);

        Department department = new Department("1", "IT", 10000.0);
        when(departmentPersistence.searchInListForDepartmentById("1")).thenReturn(department);

        Employee employee = new Employee("1", "John", "Doe", 5000.0);
        when(employeePersistence.searchInListForEmployeeById("1")).thenReturn(employee);

        assignDepartmentToEmployee.assign("1", "1");

        verify(departmentPersistence).searchInListForDepartmentById("1");
        verify(employeePersistence).searchInListForEmployeeById("1");

        List<Employee> employeeList = departmentsWithEmployees.get(department);

        assert employeeList != null;
        assert employeeList.size() == 1;
        assert employeeList.get(0).equals(employee);
        assert employee.getDepartmentName().equals(department.getName());
    }

    @Test
    public void testAssignEmployeeToDepartmentFailedDepartmentNotFound() {
        DepartmentPersistence departmentPersistence = mock(DepartmentPersistence.class);
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();
        EmployeePersistence employeePersistence = mock(EmployeePersistence.class);
        AssignDepartmentToEmployee assignDepartmentToEmployee = new AssignDepartmentToEmployee(departmentPersistence, departmentsWithEmployees, employeePersistence);

        when(departmentPersistence.searchInListForDepartmentById("1")).thenReturn(null);

        Employee employee = new Employee("1", "John", "Doe", 5000.0);
        when(employeePersistence.searchInListForEmployeeById("1")).thenReturn(employee);

        assignDepartmentToEmployee.assign("1", "1");

        verify(departmentPersistence).searchInListForDepartmentById("1");
        verify(employeePersistence, never()).searchInListForEmployeeById(any());

        List<Employee> employeeList = departmentsWithEmployees.get(null);

        assert employeeList == null;
        assert employee.getDepartmentName().equals("N/A");
    }

    @Test
    public void testAssignEmployeeToDepartmentFailedEmployeeNotFound() {
        DepartmentPersistence departmentPersistence = mock(DepartmentPersistence.class);
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();
        EmployeePersistence employeePersistence = mock(EmployeePersistence.class);
        AssignDepartmentToEmployee assignDepartmentToEmployee = new AssignDepartmentToEmployee(departmentPersistence, departmentsWithEmployees, employeePersistence);

        Department department = new Department("1", "IT", 10000.0);
        when(departmentPersistence.searchInListForDepartmentById("1")).thenReturn(department);

        when(employeePersistence.searchInListForEmployeeById("1")).thenReturn(null);

        assignDepartmentToEmployee.assign("1", "1");

        verify(departmentPersistence).searchInListForDepartmentById("1");
        verify(employeePersistence).searchInListForEmployeeById("1");

        List<Employee> employeeList = departmentsWithEmployees.get(department);

        assert employeeList == null;
        assert department.getEmployees() == null;
    }

    @Test
    public void testAssignEmployeeToDepartmentFailedBudgetNotEnough() {
        // Create mock department and employee objects
        Department department = new Department("001", "Engineering", 100000.0);
        Employee employee = new Employee("001", "John", "Doe", 50000.0);

        // Set up mock DepartmentPersistence object to return the department object
        DepartmentPersistence departmentPersistence = mock(DepartmentPersistence.class);
        when(departmentPersistence.searchInListForDepartmentById("001")).thenReturn(department);

        // Set up mock EmployeePersistence object to return the employee object
        EmployeePersistence employeePersistence = mock(EmployeePersistence.class);
        when(employeePersistence.searchInListForEmployeeById("001")).thenReturn(employee);

        // Set up departmentsWithEmployees map with the department and an existing employee with a high salary
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("002", "Jane", "Doe", 75000.0));
        Map<Department, List<Employee>> departmentsWithEmployees = new HashMap<>();
        departmentsWithEmployees.put(department, employeeList);

        // Create the AssignDepartmentToEmployee object and call the assign() method
        AssignDepartmentToEmployee assignDepartmentToEmployee = new AssignDepartmentToEmployee(
                departmentPersistence, departmentsWithEmployees, employeePersistence);
        assignDepartmentToEmployee.assign("001", "001");

        // Verify that the employee was not added to the department and that the department's employees list was not modified
        assertEquals(1, departmentsWithEmployees.get(department).size());
        assertEquals("Jane", departmentsWithEmployees.get(department).get(0).getFirstName());
        assertEquals("Doe", departmentsWithEmployees.get(department).get(0).getLastName());
    }


}