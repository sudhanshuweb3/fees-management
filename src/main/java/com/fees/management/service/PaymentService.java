package com.fees.management.service;

import com.fees.management.dto.PaymentResponseDto;
import com.fees.management.entity.Payment;

public interface PaymentService {

    PaymentResponseDto savePayment(Payment payment);
}
