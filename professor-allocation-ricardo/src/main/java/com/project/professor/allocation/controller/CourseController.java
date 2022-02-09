package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.CourseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
}

