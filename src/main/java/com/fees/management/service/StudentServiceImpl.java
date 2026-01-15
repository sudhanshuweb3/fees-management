package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.Payment;
import com.fees.management.entity.Student;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.PaymentRepository;
import com.fees.management.repository.StudentRepository;
import com.fees.management.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PaymentRepository paymentRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(course);

        Student saved = studentRepository.save(student);

        return mapToDto(saved);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return mapToDto(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public FeeSummaryResponse getFeeSummary(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        double totalFee = student.getCourse().getTotalFee();

        List<Payment> payments = paymentRepository.findByStudentId(studentId);
        double paid = payments.stream().mapToDouble(Payment::getAmount).sum();

        double remaining = totalFee - paid;

        return new FeeSummaryResponse(
                student.getId(),
                student.getName(),
                totalFee,
                paid,
                remaining
        );
    }

    // --------- Mapper ----------
    private StudentResponseDto mapToDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourse().getName()
        );
    }
}
