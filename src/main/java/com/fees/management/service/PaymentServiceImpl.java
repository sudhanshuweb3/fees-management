package com.fees.management.service;

import com.fees.management.dto.PaymentRequestDto;
import com.fees.management.dto.PaymentResponseDto;
import com.fees.management.entity.Payment;
import com.fees.management.entity.Student;
import com.fees.management.exception.ResourceNotFoundException;
import com.fees.management.repository.PaymentRepository;
import com.fees.management.repository.StudentRepository;
import com.fees.management.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + request.getStudentId()));

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setMode(request.getMode());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setStudent(student);

        Payment saved = paymentRepository.save(payment);

        PaymentResponseDto response = new PaymentResponseDto();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setMode(saved.getMode());
        response.setPaymentDate(saved.getPaymentDate());
        response.setStudentId(student.getId());
        response.setStudentName(student.getName());

        return response;
    }
}
