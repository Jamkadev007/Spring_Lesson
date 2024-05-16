package org.example.jpaspringlesson.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseMarkDetailDTO {
    private Integer id ;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
    private Integer mark;
    private LocalDate createDate ;
}
