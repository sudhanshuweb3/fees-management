package com.fees.management.controller;

import com.fees.management.dto.ApiResponse;
import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto request) {
        return studentService.createStudent(request);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ApiResponse(200, "Student deleted successfully", id);
    }

    // Fee summary endpoint
    @GetMapping("/{id}/summary")
    public FeeSummaryResponse getFeeSummary(@PathVariable Long id) {
        return studentService.getFeeSummary(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id,
                                            @RequestBody StudentRequestDto request) {
        return studentService.updateStudent(id, request);
    }

}
