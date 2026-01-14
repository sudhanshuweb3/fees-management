package com.fees.management.service;

import com.fees.management.dto.PaymentResponseDto;
import com.fees.management.entity.Payment;
import com.fees.management.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDto savePayment(Payment payment) {

        Payment saved = paymentRepository.save(payment);

        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(saved.getId());
        dto.setAmount(saved.getAmount());
        dto.setPaymentDate(saved.getPaymentDate());
        dto.setMode(saved.getMode());

        if (saved.getStudent() != null) {
            dto.setStudentId(saved.getStudent().getId());
            dto.setStudentName(saved.getStudent().getName());
        }

        return dto;
    }
}
