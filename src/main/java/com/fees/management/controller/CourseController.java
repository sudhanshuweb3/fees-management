package com.fees.management.controller;

import com.fees.management.dto.CourseRequestDto;
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

    @PostMapping
    public CourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto course) {
        return courseService.saveCourse(course);
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        return courseService.getAllCourseDtos();
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@PathVariable Long id,
                                          @RequestBody CourseRequestDto dto) {
        return courseService.updateCourse(id, dto);
    }

}
