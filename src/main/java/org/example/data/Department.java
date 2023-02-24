package org.example.data;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String ID;
    private String name;
    private double budget;
    private List<Employee> employees;


    public Department(String ID, String name, double budget) {
        this.ID = ID;
        this.name = name;
        this.budget = budget;
        this.employees = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}