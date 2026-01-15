package com.fees.management.mapper;

import com.fees.management.dto.StudentResponse;
import com.fees.management.entity.Student;

public class StudentMapper {

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourse().getName()
        );
    }
}
