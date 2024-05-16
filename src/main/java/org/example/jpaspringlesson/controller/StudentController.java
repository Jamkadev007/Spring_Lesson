package org.example.jpaspringlesson.controller;

import org.example.jpaspringlesson.dto.StudentDTO;
import org.example.jpaspringlesson.dto.StudentFilterDTO;
import org.example.jpaspringlesson.enums.Gender;
import org.example.jpaspringlesson.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<StudentDTO> all() {
        return studentService.getAll();
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<StudentDTO> byId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(studentService.byId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        StudentDTO response = studentService.create(studentDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        studentService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/byname/{name}")
    public List<StudentDTO> byName(@PathVariable("name") String name) {
        List<StudentDTO> dtoList = studentService.getByName(name);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList;

    }

    @GetMapping("/bysurname/{surname}")
    public List<StudentDTO> bySurname(@PathVariable("surname") String surname) {
        List<StudentDTO> dtoList = studentService.getBySurname(surname);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList;
    }

    @GetMapping("/bylevel/{level}")
    public List<StudentDTO> byLevel(@PathVariable("level") Integer level) {
        List<StudentDTO> dtoList = studentService.getByLevel(level);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList ;
    }

    @GetMapping("/byage/{age}")
    public List<StudentDTO> byAge(@PathVariable("age") Integer age) {
        List<StudentDTO> dtoList = studentService.getByAge(age);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList ;
    }

    @GetMapping("/gender/{gender}")
    public List<StudentDTO> byGender(@PathVariable("gender") String gender) {
        List<StudentDTO> dtoList = studentService.getByGender(gender);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList ;
    }

    @GetMapping("/bydate/{date}")
    public List<StudentDTO> byDate(@PathVariable("date") LocalDate date) {
        List<StudentDTO> dtoList = studentService.getByDate(date);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList ;
    }

    @GetMapping("/between/{from}/{to}")
    public List<StudentDTO> between(@PathVariable("from") LocalDate from, @PathVariable("to") LocalDate to) {
        List<StudentDTO> dtoList = studentService.getByBetween(from , to);
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList ;

    }

    @PostMapping("/age")
    public ResponseEntity<List<StudentDTO>> byAge(@RequestBody List<Integer> ageList) {
        List<StudentDTO> studentDTOList = studentService.getByAgeIn(ageList);
        return ResponseEntity.ok().body(studentDTOList);

    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<StudentDTO>> pageable(@RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "size", defaultValue = "2") int size) {
        PageImpl<StudentDTO> studentDTOList = studentService.pagination(page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/paginationWithLevel/{level}")
    public ResponseEntity<PageImpl<StudentDTO>> pageableWithLevel(@PathVariable("level") Integer level,
                                                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "size", defaultValue = "2") int size)
    {
        PageImpl<StudentDTO> studentDTOList = studentService.paginationWithLevel(level,page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/paginationWithGender/{gender}")
    public ResponseEntity<PageImpl<StudentDTO>> pageableWithGender(@PathVariable("gender") Gender gender,
                                                                   @RequestParam(value = "page",defaultValue = "1") int page,
                                                                   @RequestParam(value = "size",defaultValue = "2") int size)
    {
        PageImpl<StudentDTO> studentDTOS = studentService.paginationWithGender(gender,page-1,size);
        return ResponseEntity.ok().body(studentDTOS);

    }



    @PostMapping("/filter")
    public ResponseEntity<PageImpl<StudentDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody StudentFilterDTO filter) {
        PageImpl<StudentDTO> studentDTOList = studentService.paginationWithName(filter.getName(), page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);

    }











}
