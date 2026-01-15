package com.fees.management.controller;

import com.fees.management.dto.PaymentResponseDto;
import com.fees.management.entity.Payment;
import com.fees.management.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDto createPayment(@Valid @RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }
}
