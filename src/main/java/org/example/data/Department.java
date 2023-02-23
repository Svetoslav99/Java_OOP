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

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}