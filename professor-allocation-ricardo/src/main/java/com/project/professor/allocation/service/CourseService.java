package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private final AllocationRepository allocationRepository;


    public CourseService(CourseRepository courseRepository, AllocationRepository allocationRepository) {
        super();
        this.courseRepository = courseRepository;
        this.allocationRepository = allocationRepository;

    }


    public Course findById(Long id)
    {
        if (id != null)
        {
            Optional<Course> courseOptional = courseRepository.findById(id);
            Course course = courseOptional.orElse(null);
            return course;
        } else {
            return null;
        }
    }

    public List<Course> findAll(String name) {
        if (name == null) {
            return courseRepository.findAll();
        } else {
            return courseRepository.findByNameContaining(name);
        }
    }

    public Course create(Course course)
    {
        course.setId(null);
        Course courseNew = courseRepository.save(course);
        return courseNew;
    }

    public Course update(Course course)
    {
        Long id = course.getId();

        if (id != null && courseRepository.existsById(id))
        {
            Course courseNew = courseRepository.save(course);
            return courseNew;
        }
        else
        {
            return null;
        }
    }

    public void deleteById(Long id)
    {
        if (id != null && courseRepository.existsById(id))
        {
            courseRepository.deleteById(id);
        }
    }

    public void deleteAll()
    {
        courseRepository.deleteAll();
    }

    private Course saveInternal(Course course) {
        course = courseRepository.save(course);

        List<Allocation> allocations = allocationRepository.findByCourseId(course.getId());
        course.setAllocations(allocations);

        return course;
    }
}

