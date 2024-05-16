package org.example.jpaspringlesson.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.jpaspringlesson.enums.Gender;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name ;

    @Column(name = "surname")
    private String surname ;

    @Column(name = "level")
    private Integer level;

    @Column(name = "age")
    private Integer  age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "createdDate")
    private LocalDate createdDate = LocalDate.now();
}
