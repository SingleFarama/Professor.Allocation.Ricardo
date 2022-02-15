package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void findAll() {
        List<Department> departments = departmentService.findAll();

        departments.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Department department = departmentService.findById(id);

        System.out.println(department);
    }

    @Test
    public void create() {
        Department department = new Department();
        department.setId(null);
        department.setName("Department 1");

        department = departmentService.create(department);

        System.out.println(department);
    }

    @Test
    public void update() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 2");

        department = departmentService.update(department);

        System.out.println(department);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        departmentService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        departmentService.deleteAll();
    }
}
