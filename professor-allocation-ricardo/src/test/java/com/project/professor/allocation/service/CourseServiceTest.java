package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

    @Autowired
    CourseService courseService;

    @Test
    public void create() throws ParseException {
        Course course = new Course();
        course.setId(10L);
        course.setName("História");

        Course courseNew = courseService.create(course);

        System.out.println(courseNew);
    }

    @Test
    public void update() throws ParseException {
        Course course = new Course();
        course.setId(10L);
        course.setName("Filosofia");


        Course courseNew = CourseService.update(course);

        System.out.println(courseNew);
    }

}
