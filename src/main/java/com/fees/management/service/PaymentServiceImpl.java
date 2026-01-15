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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);


    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto request) {

        log.info("Creating payment for studentId: {}", request.getStudentId());


        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + request.getStudentId()));

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setMode(request.getMode());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setStudent(student);

        Payment saved = paymentRepository.save(payment);
        log.info("Payment saved successfully, amount: {}", saved.getAmount());


        PaymentResponseDto response = new PaymentResponseDto();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setMode(saved.getMode());
        response.setPaymentDate(saved.getPaymentDate());
        response.setStudentId(student.getId());
        response.setStudentName(student.getName());

        return response;
    }
    @Override
    public PaymentResponseDto updatePayment(Long id, PaymentRequestDto dto) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        payment.setAmount(dto.getAmount());
        payment.setMode(dto.getMode());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setStudent(student);

        Payment updated = paymentRepository.save(payment);

        return mapToDto(updated);
    }
    public PaymentResponseDto mapToDto(Payment payment) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setMode(payment.getMode());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setStudentId(payment.getStudent().getId());
        dto.setStudentName(payment.getStudent().getName());
        return dto;
    }

}
