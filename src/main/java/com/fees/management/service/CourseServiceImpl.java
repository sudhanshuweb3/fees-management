package com.fees.management.service;


import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<CourseResponseDto> getAllCourseDtos() {
        return courseRepository.findAll()
                .stream()
                .map(course -> {
                    CourseResponseDto dto = new CourseResponseDto();
                    dto.setId(course.getId());
                    dto.setName(course.getName());
                    dto.setTotalFee(course.getTotalFee());
                    dto.setTotalStudents(
                            course.getStudents() == null ? 0 : course.getStudents().size()
                    );
                    return dto;
                })
                .toList();
    }



}
