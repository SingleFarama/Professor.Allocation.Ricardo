package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    public void findAll() {
        List<Course> courses = courseService.findAll();

        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Course course = courseService.findById(id);

        System.out.println(course);
    }

    @Test
    public void create() {
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");

        course = courseService.create(course);

        System.out.println(course);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");

        course = courseService.update(course);

        System.out.println(course);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        courseService.deleteById(id);
    }

    @Test
    public void deleteAll() {
        courseService.deleteAll();
    }
}
