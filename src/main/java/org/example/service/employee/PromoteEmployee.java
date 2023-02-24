package org.example.service.employee;

import org.example.data.Department;
import org.example.data.Employee;
import org.example.persistance.DepartmentPersistence;
import org.example.persistance.EmployeePersistence;
import org.example.service.calculate.NewSalary;
import org.example.service.calculate.NeededMoneyFromBudget;
import org.example.service.writer.WriteToFile;

import java.util.List;
import java.util.Map;

public class PromoteEmployee {
    private EmployeePersistence employeePersistence;
    private DepartmentPersistence departmentPersistence;
    private Map<Department, List<Employee>> departmentsWithEmployees;
    private final WriteToFile writeToOutputFile = new WriteToFile();

    public PromoteEmployee(EmployeePersistence employeePersistence, DepartmentPersistence departmentPersistence,
                           Map<Department, List<Employee>> departmentsWithEmployees) {
        this.employeePersistence = employeePersistence;
        this.departmentPersistence = departmentPersistence;
        this.departmentsWithEmployees = departmentsWithEmployees;
    }

    public void promote(String employeeId, double promotionPercentage) {
        if (!this.isValidPromotionPercentage(promotionPercentage))
            return;

        Employee foundEmployee = employeePersistence.searchInListForEmployeeById(employeeId);

        if (foundEmployee == null) {
            String message = "Employee with ID " + employeeId + " does not exist!";
            System.out.println(message);
            writeToOutputFile.write(message);
            return;
        }

        double newSalary = NewSalary.calc(foundEmployee.getYearlySalary(), promotionPercentage);

        Department employeeDepartment = this.departmentPersistence.getDepartmentByName(foundEmployee.getDepartmentName());

        List<Employee> employeeList = null;

        if (foundEmployee.getDepartmentName().equals("N/A")) {
            foundEmployee.setYearlySalary(newSalary);
        } else {
            employeeList = this.departmentsWithEmployees.get(employeeDepartment);

            employeeList.remove(foundEmployee);
            foundEmployee.setYearlySalary(newSalary);
            employeeList.add(foundEmployee);
        }

        double neededMoneyFromBudget = NeededMoneyFromBudget.calcByEmployeesAndIncomingEmployee(employeeList, foundEmployee);

        if (neededMoneyFromBudget < 0) {
            String message = "Department " + employeeDepartment.getID() + "’s budget does not allow for such a high promotion!";
            System.out.println(message);
            writeToOutputFile.write(message);
        } else {
            this.departmentsWithEmployees.put(employeeDepartment, employeeList);
        }

    }

    private boolean isValidPromotionPercentage(double promotionPercentage) {
        if (promotionPercentage < 0) {
            String message = "The promotion percentage needs to be a positive floating number";
            System.out.println(message);
            writeToOutputFile.write(message);
            return false;
        }

        return true;
    }
}
