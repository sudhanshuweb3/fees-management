package com.fees.management.service;

import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);

    List<CourseResponseDto> getAllCourseDtos();
}
