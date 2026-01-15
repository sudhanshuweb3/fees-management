package com.fees.management.service;


import com.fees.management.dto.CourseRequestDto;
import com.fees.management.dto.CourseResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.mapper.CourseMapper;
import com.fees.management.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);


    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponseDto saveCourse(CourseRequestDto dto) {

        log.info("Creating course: {}", dto.getName());

        Course course = new Course();
        course.setName(dto.getName());
        course.setTotalFee(dto.getTotalFee());

        Course saved = courseRepository.save(course);

        log.info("Course created with id: {}", saved.getId());

        return CourseMapper.toDto(saved);
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
                .map(CourseMapper::toDto)
                .toList();
    }
    private CourseResponseDto mapToResponseDto(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setTotalFee(course.getTotalFee());
        dto.setTotalStudents(
                course.getStudents() == null ? 0 : course.getStudents().size()
        );
        return dto;
    }


}
