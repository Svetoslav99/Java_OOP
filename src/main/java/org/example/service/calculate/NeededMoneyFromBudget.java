package org.example.service.calculate;


import org.example.data.Employee;

import java.util.List;

public abstract class NeededMoneyFromBudget {
    public static double calcByEmployeesAndIncomingEmployee(List<Employee> employees,
                                                            Employee incomingEmployee) {
        if(employees == null) {
            return incomingEmployee.getYearlySalary();
        }

        double salariesSum = employees.stream()
                .mapToDouble(Employee::getYearlySalary)
                .sum();

        return salariesSum + incomingEmployee.getYearlySalary();
    }
}
