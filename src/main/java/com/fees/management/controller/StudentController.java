package com.fees.management.controller;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.Student;
import com.fees.management.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create student (still accepts entity as input)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // Return DTOs instead of entities (FIXES infinite recursion)
    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudentDtos();
    }

    // Optional: if you still want student by id, return DTO
    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentDtoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // Business endpoint (already perfect)
    @GetMapping("/{id}/summary")
    public FeeSummaryResponse getFeeSummary(@PathVariable Long id) {
        return studentService.getFeeSummary(id);
    }
}
