package org.example.jpaspringlesson.controller;


import org.example.jpaspringlesson.dto.CourseDTO;
import org.example.jpaspringlesson.entity.CourseEntity;
import org.example.jpaspringlesson.repository.StudentRepository;
import org.example.jpaspringlesson.service.CourseService;
import org.example.jpaspringlesson.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")

public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO response = courseService.create(courseDTO);
        return  ResponseEntity.ok().body(response);

    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        return  ResponseEntity.ok().body(courseService.getCourseById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO) {
        courseService.update(id , courseDTO);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Integer id) {
        courseService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<CourseDTO>> getCoursesByName(@PathVariable String name) {
        return ResponseEntity.ok().body(courseService.getByname(name));
    }

    @GetMapping("/byPrice/{price}")
    public ResponseEntity<List<CourseDTO>> getCoursesByPrice(@PathVariable Integer price) {
        return ResponseEntity.ok().body(courseService.getByPrice(price));
    }

    @GetMapping("/byDuration/{duration}")
    public ResponseEntity<List<CourseDTO>> getCoursesByDuration(@PathVariable Integer duration) {
        return ResponseEntity.ok().body(courseService.getByDuration(duration));
    }

    @GetMapping("/BetweenPrice/{from}/{to}")
    public ResponseEntity<List<CourseDTO>> getBetweenPrice(@PathVariable Integer from, @PathVariable Integer to) {
        List<CourseDTO> courseDTOList = courseService.getByBetweenPrice(from,to);
        if (courseDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(courseDTOList);

    }

    @GetMapping("/BetweenCreatedDate/{from}/{to}")
    public ResponseEntity<List<CourseDTO>> getBetweenCreatedDate(@PathVariable LocalDate from, @PathVariable LocalDate to) {
        return ResponseEntity.ok().body(courseService.getBeweenCreatedDate(from,to));

    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<CourseDTO>> getPagination(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                                             @RequestParam(value = "size" , defaultValue = "2") Integer size)
    {
        PageImpl<CourseDTO> courseDTOS = courseService.pagination(page-1,size);
        return ResponseEntity.ok().body(courseDTOS);
    }

    @GetMapping("/paginationByPrice/{price}")
    public ResponseEntity<PageImpl<CourseDTO>> getPaginationByPrice(@PathVariable Double price ,
                                                                    @RequestParam(value = "page" , defaultValue = "1") Integer page ,
                                                                    @RequestParam(value = "size" ,defaultValue = "1") Integer size)
    {
        PageImpl<CourseDTO> courseDTOS = courseService.paginationByPrice(price , page-1,size);
        return ResponseEntity.ok().body(courseDTOS);
    }

    @GetMapping("/paginationByTwoPrice/{to}/{from}")
    public ResponseEntity<PageImpl<CourseDTO>> getPaginationByTwoPrice(@PathVariable Double to,
                                                                       @PathVariable Double from,
                                                                       @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                                                       @RequestParam(value = "size" , defaultValue = "1") Integer size)
    {
        PageImpl<CourseDTO> courseDTOS = courseService.paginationByTwoPrice(to,from,page-1,size);
        return ResponseEntity.ok().body(courseDTOS);

    }



}
