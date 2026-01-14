package com.fees.management.dto;

public class FeeSummaryResponse {

    private Long studentId;
    private String studentName;
    private String course;
    private Double totalFee;
    private Double paid;
    private Double pending;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getPending() {
        return pending;
    }

    public void setPending(Double pending) {
        this.pending = pending;
    }
}
