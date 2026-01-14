package com.fees.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate paymentDate;

    private String mode;

    @ManyToOne
    @JoinColumn(name = "student_id")
    //@JsonIgnoreProperties("payments") // prevents infinite loop
    private Student student;

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getMode() {
        return mode;
    }

    public Student getStudent() {
        return student;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
