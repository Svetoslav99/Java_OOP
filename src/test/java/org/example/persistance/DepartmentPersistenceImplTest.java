package org.example.persistance;

import junit.framework.TestCase;
import org.example.data.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class DepartmentPersistenceImplTest extends TestCase {
    private DepartmentPersistenceImpl departmentPersistence;

    @Test
    public void testAddDepartment() {
        this.departmentPersistence = new DepartmentPersistenceImpl();

        Department department1 = new Department("001", "Department 1", 10000);
        Department department2 = new Department("002", "Department 2", 20000);

        // add department1
        departmentPersistence.addDepartment(department1);
        List<Department> departments = departmentPersistence.getAllDepartments();
        Assertions.assertEquals(1, departments.size());
        Assertions.assertEquals(department1, departments.get(0));

        // add department2
        departmentPersistence.addDepartment(department2);
        departments = departmentPersistence.getAllDepartments();
        Assertions.assertEquals(2, departments.size());
        Assertions.assertEquals(department2, departments.get(1));
    }

    @Test
    public void testGetAllDepartments() {
        this.departmentPersistence = new DepartmentPersistenceImpl();

        List<Department> departments = departmentPersistence.getAllDepartments();
        Assertions.assertTrue(departments.isEmpty());

        Department department1 = new Department("001", "Department 1", 20000);
        departmentPersistence.addDepartment(department1);

        departments = departmentPersistence.getAllDepartments();
        Assertions.assertEquals(1, departments.size());
        Assertions.assertEquals(department1, departments.get(0));
    }

    @Test
    public void testGetDepartmentByName() {
        this.departmentPersistence = new DepartmentPersistenceImpl();

        Department department1 = new Department("001", "Department 1", 10000);
        departmentPersistence.addDepartment(department1);

        Department department2 = new Department("002", "Department 2", 20000);
        departmentPersistence.addDepartment(department2);

        Department department = departmentPersistence.getDepartmentByName("Department 2");
        Assertions.assertEquals(department2, department);
    }

    @Test
    public void testSearchInListForDepartmentById() {
        this.departmentPersistence = new DepartmentPersistenceImpl();

        Department department1 = new Department("001", "Department 1", 30000);
        departmentPersistence.addDepartment(department1);

        Department department = departmentPersistence.searchInListForDepartmentById("001");
        Assertions.assertEquals(department1, department);

        department = departmentPersistence.searchInListForDepartmentById("002");
        Assertions.assertNull(department);
    }

    @Test
    public void testAddDuplicateDepartment() {
        this.departmentPersistence = new DepartmentPersistenceImpl();

        Department department1 = new Department("001", "Department 1",10000);
        departmentPersistence.addDepartment(department1);

        // add department with the same id
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Department duplicateDepartment = new Department("001", "Department 1 - Duplicate",10000);
            departmentPersistence.addDepartment(duplicateDepartment);
        });
    }
}