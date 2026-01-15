package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto request);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);

    void deleteStudent(Long id);

    FeeSummaryResponse getFeeSummary(Long studentId);

    StudentResponseDto updateStudent(Long id, StudentRequestDto request);

}
