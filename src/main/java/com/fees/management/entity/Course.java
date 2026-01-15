package com.fees.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name cannot be empty")
    private String name;
    @NotNull(message = "Total fee is required")
    @Positive(message = "Total fee must be positive")
    private Double totalFee;

    @OneToMany(mappedBy = "course")
    //@JsonIgnoreProperties("course")
    private List<Student> students;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getTotalFee() { return totalFee; }
    public List<Student> getStudents() { return students; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }
    public void setStudents(List<Student> students) { this.students = students; }
}

