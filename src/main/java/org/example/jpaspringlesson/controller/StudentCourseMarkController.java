package org.example.jpaspringlesson.controller;


import org.example.jpaspringlesson.dto.StudentCourseMarkDTO;
import org.example.jpaspringlesson.dto.StudentCourseMarkDetailDTO;
import org.example.jpaspringlesson.service.CourseService;
import org.example.jpaspringlesson.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/studentCourseMark")

public class StudentCourseMarkController {

    @Autowired
    private StudentCourseMarkService studentCourseMarkService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<StudentCourseMarkDTO> createCourse(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkDTO response = studentCourseMarkService.create(studentCourseMarkDTO);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentCourseMarkDTO> updateCourse(@PathVariable int id, @RequestBody StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkDTO studentCourseMarkDTO1 = studentCourseMarkService.update(id, studentCourseMarkDTO);
        return ResponseEntity.ok().body(studentCourseMarkDTO1);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<StudentCourseMarkDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(studentCourseMarkService.findById(id));
    }

    @GetMapping("/byIdDetail/{id}")
    public ResponseEntity<StudentCourseMarkDetailDTO> findByIdDetail(@PathVariable int id) {
        return ResponseEntity.ok().body(studentCourseMarkService.byIdDetail(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<StudentCourseMarkDTO> deleteById(@PathVariable int id) {
        return ResponseEntity.ok().body(studentCourseMarkService.delete(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentCourseMarkDetailDTO>> getAll() {
        return ResponseEntity.ok().body(studentCourseMarkService.getAll());
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<StudentCourseMarkDetailDTO>> getByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok().body(studentCourseMarkService.getByDate(date));
    }

    @GetMapping("/getByTwoDate/{from}/{to}")
    public ResponseEntity<List<StudentCourseMarkDetailDTO>> getByTwoDate(@PathVariable LocalDate from, @PathVariable LocalDate to) {
        return ResponseEntity.ok().body(studentCourseMarkService.getByTwoDate(from, to));

    }

    @GetMapping("/getByMarkCreatedDateSort/{studentId}")
    public ResponseEntity<List<StudentCourseMarkDetailDTO>> getByMarkCreatedDate(@PathVariable int studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getByMarkSortDate(studentId));
    }

    @GetMapping("/getLastMark/{studentId}")
    public ResponseEntity<StudentCourseMarkDetailDTO> getLastMark(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getLastMark(studentId));
    }

    @GetMapping("/getLastThreeMark/{studentId}")
    public ResponseEntity<List<StudentCourseMarkDetailDTO>> getLastThreeMark(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getLastThreeMark(studentId));
    }

    @GetMapping("/getFirstMark/{studentId}")
    public ResponseEntity<StudentCourseMarkDetailDTO> getFirstMark(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getFirstMark(studentId));
    }

    @GetMapping("/getMarkFirstCourse/{studentId}/{courseId}")
    public ResponseEntity<StudentCourseMarkDetailDTO> getMarkFirstCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getFirstMarkByCourse(studentId, courseId));


    }

    @GetMapping("/getGreatMarkStudentCourse/{studentId}/{courseId}")
    public ResponseEntity<StudentCourseMarkDetailDTO> getGreatMarkStudentCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getMarkGreat(studentId, courseId));
    }

    @GetMapping("/avg/{studentId}")
    public ResponseEntity<Integer> getAvg(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.avg(studentId));
    }

    @GetMapping("/getAvgCourse/{studentId}/{courseId}")
    public ResponseEntity<Integer> getAvgCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.avgCourse(studentId, courseId));
    }

    @GetMapping("/getThan/{studentId}/{mark}")
    public ResponseEntity<Integer> getThanMark(@PathVariable Integer studentId, @PathVariable Integer mark) {
        return ResponseEntity.ok().body(studentCourseMarkService.getGrethanMark(studentId, mark));
    }

    @GetMapping("/getGreatMarkByCourse/{courseId}")
    public ResponseEntity<Integer> getGreatMarkByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getGreatMarkByCourse(courseId));
    }

    @GetMapping("/getAvgByCourse/{courseId}")
    public ResponseEntity<Integer> getAvgByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getAvgByCourse(courseId));
    }

    @GetMapping("/getCountByCourse/{courseId}")
    public ResponseEntity<Integer> getCountByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getCountByCourse(courseId));
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<StudentCourseMarkDetailDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                           @RequestParam(value = "size", defaultValue = "2") int size) {
        PageImpl<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOS = studentCourseMarkService.pagination(page - 1, size);
        return ResponseEntity.ok().body(studentCourseMarkDetailDTOS);

    }

    @GetMapping("paginationWithSort/{studentId}")
    public ResponseEntity<PageImpl<StudentCourseMarkDetailDTO>> paginationWithSort(
            @PathVariable Integer studentId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size) {
        PageImpl<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOS = studentCourseMarkService.paginationWithSort(studentId ,page - 1, size);
        return ResponseEntity.ok().body(studentCourseMarkDetailDTOS);
    }

    @GetMapping("/paginationWithSortCourse/{courseId}")
    public ResponseEntity<PageImpl<StudentCourseMarkDetailDTO>> paginationWithSortCourse(
            @PathVariable Integer courseId ,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size" ,defaultValue = "2")Integer size) {
        PageImpl<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOS = studentCourseMarkService.paginationWithCourseId(courseId,page-1,size);
        return ResponseEntity.ok().body(studentCourseMarkDetailDTOS);
    }

    @GetMapping("/MarkByStudentIdInnerJoin/{studentId}")
    public ResponseEntity<?> MarkByStudentIdInnerJoin(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getMarkByStudentId(studentId));
    }

    @GetMapping("/MarkByCourseId/{courseId}")
    public ResponseEntity<List<?>> MarkByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentCourseMarkService.getByMarkCourseId(courseId));
    }



}
