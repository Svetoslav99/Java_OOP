package org.example.data;
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private String departmentName;

    public Employee(String id, String firstName, String lastName, double yearlySalary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary = yearlySalary;
        this.departmentName = "N/A";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
