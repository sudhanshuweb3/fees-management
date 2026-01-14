package com.fees.management.controller;

import com.fees.management.entity.Course;
import com.fees.management.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // POST /courses
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    // GET /courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
