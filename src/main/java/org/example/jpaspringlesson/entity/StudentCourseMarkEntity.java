package org.example.jpaspringlesson.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "studentCourseMark")
public class StudentCourseMarkEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "courseId")
    private Integer courseId;


    @Column(name = "mark")
    private Integer mark;

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();







}
