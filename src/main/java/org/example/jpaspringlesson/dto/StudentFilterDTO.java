package org.example.jpaspringlesson.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class StudentFilterDTO {
    private Integer id;
    private String name ;
    private String surname ;
    private LocalDate birthday ;
}
