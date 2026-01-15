package com.fees.management.service;

import com.fees.management.dto.FeeSummaryResponse;
import com.fees.management.dto.StudentRequestDto;
import com.fees.management.dto.StudentResponseDto;
import com.fees.management.entity.Course;
import com.fees.management.entity.Payment;
import com.fees.management.entity.Student;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.mapper.StudentMapper;
import com.fees.management.repository.CourseRepository;
import com.fees.management.repository.PaymentRepository;
import com.fees.management.repository.StudentRepository;
import com.fees.management.service.StudentService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);


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

        log.info("Creating student with email: {}", request.getEmail());

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> {
                    log.error("Course not found with id: {}", request.getCourseId());
                    return new ResourceNotFoundException("Course not found");
                });

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(course);

        Student saved = studentRepository.save(student);

        log.info("Student created successfully with id: {}", saved.getId());

        return StudentMapper.toDto(saved);
    }


    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {

        log.info("Fetching student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found");
                });

        return StudentMapper.toDto(student);
    }


    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // delete all payments of this student first
        paymentRepository.deleteByStudentId(id);

        // tthen delete student
        studentRepository.delete(student);

        System.out.println("Student deleted successfully: " + id);
    }


    @Override
    public FeeSummaryResponse getFeeSummary(Long studentId) {

        log.info("Fetching fee summary for student: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("Student not found for summary: {}", studentId);
                    return new ResourceNotFoundException("Student not found");
                });

        double totalFee = student.getCourse().getTotalFee();

        List<Payment> payments = paymentRepository.findByStudentId(studentId);
        double paid = payments.stream().mapToDouble(Payment::getAmount).sum();

        double remaining = totalFee - paid;

        log.info("Fee summary -> total: {}, paid: {}, pending: {}", totalFee, paid, remaining);

        return new FeeSummaryResponse(
                student.getId(),
                student.getName(),
                totalFee,
                paid,
                remaining
        );
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(course);

        Student updated = studentRepository.save(student);

        return StudentMapper.toDto(updated);
    }



}
