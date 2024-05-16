package org.example.jpaspringlesson.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)

public class StudentCourseMarkDTO {
    private Integer id ;
    private Integer studentId;
    private Integer courseId;
    private Integer mark;
    private LocalDate createDate ;
}
