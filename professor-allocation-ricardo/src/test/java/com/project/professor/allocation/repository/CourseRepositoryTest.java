package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findAll() {
        List<Course> courses = courseRepository.findAll();

        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Course course = courseRepository.findById(id).orElse(null);

        System.out.println(course);
    }

    @Test
    public void create() {
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");

        course = courseRepository.save(course);

        System.out.println(course);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");

        course = courseRepository.save(course);

        System.out.println(course);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        courseRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
        courseRepository.deleteAllInBatch();
    }
}

