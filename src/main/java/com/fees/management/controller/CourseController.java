package com.fees.management.controller;

import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // POST - still accepts entity
    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    // GET - MUST return DTO
    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        return courseService.getAllCourseDtos();
    }
}
