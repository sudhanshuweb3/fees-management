package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.entity.*;
import com.fees.management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public FeeSummaryResponse getFeeSummary(Long studentId) {

        Student student = getStudentById(studentId);

        double totalFee = student.getCourse().getTotalFee();

        double paid = 0.0;
        if (student.getPayments() != null) {
            paid = student.getPayments()
                    .stream()
                    .mapToDouble(Payment::getAmount)
                    .sum();
        }

        double pending = totalFee - paid;

        FeeSummaryResponse response = new FeeSummaryResponse();
        response.setStudentId(student.getId());
        response.setStudentName(student.getName());
        response.setCourse(student.getCourse().getName());
        response.setTotalFee(totalFee);
        response.setPaid(paid);
        response.setPending(pending);

        return response;
    }

}
