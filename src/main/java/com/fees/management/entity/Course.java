package com.fees.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
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

