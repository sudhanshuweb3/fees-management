package com.fees.management.controller;

import com.fees.management.dto.FeeSummaryResponse;
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


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/summary")
    public FeeSummaryResponse getFeeSummary(@PathVariable Long id) {
        return studentService.getFeeSummary(id);
    }

}
