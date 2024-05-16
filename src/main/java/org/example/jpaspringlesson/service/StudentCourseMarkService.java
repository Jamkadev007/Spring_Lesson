package org.example.jpaspringlesson.service;

import org.example.jpaspringlesson.dto.CourseDTO;
import org.example.jpaspringlesson.dto.StudentCourseMarkDTO;
import org.example.jpaspringlesson.dto.StudentCourseMarkDetailDTO;
import org.example.jpaspringlesson.dto.StudentDTO;
import org.example.jpaspringlesson.entity.StudentCourseMarkEntity;
import org.example.jpaspringlesson.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentCourseMarkService {

    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    public StudentCourseMarkDTO create(StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity studentCourseMarkEntity = new StudentCourseMarkEntity();
        studentCourseMarkEntity.setCourseId(studentCourseMarkDTO.getCourseId());
        studentCourseMarkEntity.setStudentId(studentCourseMarkDTO.getStudentId());
        studentCourseMarkEntity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkRepository.save(studentCourseMarkEntity);
        studentCourseMarkDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDTO;
    }


    public StudentCourseMarkDTO update(int id, StudentCourseMarkDTO studentCourseMarkDTO) {
        StudentCourseMarkEntity studentCourseMarkEntity = get(id);
        studentCourseMarkEntity.setCourseId(studentCourseMarkDTO.getCourseId());
        studentCourseMarkEntity.setStudentId(studentCourseMarkDTO.getStudentId());
        studentCourseMarkEntity.setMark(studentCourseMarkDTO.getMark());
        studentCourseMarkRepository.save(studentCourseMarkEntity);
        studentCourseMarkDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDTO;

    }


    public StudentCourseMarkEntity get(Integer id) {
        return studentCourseMarkRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Course not found");
        });
    }

    public StudentCourseMarkDTO findById(int id) {
        StudentCourseMarkEntity studentCourseMarkEntity = get(id);
        StudentCourseMarkDTO studentCourseMarkDTO = new StudentCourseMarkDTO();
        studentCourseMarkDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDTO.setCourseId(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDTO.setStudentId(studentCourseMarkEntity.getStudentId());
        studentCourseMarkDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDTO;
    }

    public StudentCourseMarkDetailDTO byIdDetail(int id) {
        StudentCourseMarkEntity studentCourseMarkEntity = get(id);
        StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
        CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
        StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
        studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
        studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
        studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDetailDTO;

    }

    public StudentCourseMarkDTO delete(int id) {
        StudentCourseMarkEntity studentCourseMarkEntity = get(id);
        studentCourseMarkRepository.delete(studentCourseMarkEntity);
        StudentCourseMarkDTO studentCourseMarkDTO = new StudentCourseMarkDTO();
        studentCourseMarkDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDTO.setCourseId(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDTO.setStudentId(studentCourseMarkEntity.getStudentId());
        studentCourseMarkDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDTO;


    }

    public List<StudentCourseMarkDetailDTO> getAll() {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);


        }
        return studentCourseMarkDetailDTOList;

    }

    public List<StudentCourseMarkDetailDTO> getByDate(LocalDate date) {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findByCreatedDate(date);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);


        }
        return studentCourseMarkDetailDTOList;
    }

    public List<StudentCourseMarkDetailDTO> getByTwoDate(LocalDate from, LocalDate to) {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findByCreatedDateBetween(from, to);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);


        }
        return studentCourseMarkDetailDTOList;
    }

    public List<StudentCourseMarkDetailDTO> getByMarkSortDate(Integer studentId) {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findByStudentIdOrderByCreatedDateDesc(studentId);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);


        }
        return studentCourseMarkDetailDTOList;
    }

    public StudentCourseMarkDetailDTO getLastMark(Integer studentId) {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findByStudentIdOrderByCreatedDateDesc(studentId);
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkEntityList.iterator().next();
        StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
        StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
        CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
        studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
        studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
        return studentCourseMarkDetailDTO;

    }


    public List<StudentCourseMarkDetailDTO> getLastThreeMark(int studentId) {
        Iterable<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.findTop3ByStudentIdOrderByMarkDesc(studentId);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);

        }
        return studentCourseMarkDetailDTOList;
    }

    public StudentCourseMarkDetailDTO getFirstMark(Integer studentId) {
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkRepository.findTop1ByStudentIdOrderByCreatedDateAsc(studentId);
        StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
        StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
        CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
        studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
        studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
        studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());

        return studentCourseMarkDetailDTO;
    }

    public StudentCourseMarkDetailDTO getFirstMarkByCourse(Integer studentId, Integer courseId) {
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkRepository.findTop1ByStudentIdAndCourseIdOrderByCreatedDateAsc(studentId, courseId);
        StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
        StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
        CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
        studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
        studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());

        studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());

        return studentCourseMarkDetailDTO;


    }

    public StudentCourseMarkDetailDTO getMarkGreat(Integer studentId, Integer courseId) {
        StudentCourseMarkEntity studentCourseMarkEntity = studentCourseMarkRepository.findTop1ByStudentIdAndCourseIdOrderByMarkDesc(studentId, courseId);
        StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
        StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
        CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
        studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
        studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
        studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
        studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());

        studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());

        return studentCourseMarkDetailDTO;
    }

    public Integer avg(Integer studentId) {

        return studentCourseMarkRepository.avg(studentId);
    }

    public Integer avgCourse(Integer studentId, Integer courseId) {
        return studentCourseMarkRepository.avgCourse(studentId, courseId);
    }

    public Integer getGrethanMark(Integer studentId, Integer mark) {
        return studentCourseMarkRepository.getThanMark(studentId, mark);
    }

    public Integer getGreatMarkByCourse(Integer courseId) {
        return studentCourseMarkRepository.getGreatMarkByCourse(courseId);
    }

    public Integer getAvgByCourse(Integer courseId) {
        return studentCourseMarkRepository.getAvgByCourse(courseId);
    }

    public Integer getCountByCourse(Integer courseId) {
        return studentCourseMarkRepository.getCountByCourse(courseId);
    }

    public PageImpl<StudentCourseMarkDetailDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageImpl<StudentCourseMarkEntity> studentCourseMarkEntityPage = studentCourseMarkRepository.findAll(pageable);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityPage.getContent()) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);

        }
        Long totalCount = studentCourseMarkEntityPage.getTotalElements();
        return new PageImpl<>(studentCourseMarkDetailDTOList, pageable, totalCount);

    }

    public PageImpl<StudentCourseMarkDetailDTO> paginationWithSort(Integer studentId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        PageImpl<StudentCourseMarkEntity> studentCourseMarkEntityPage = studentCourseMarkRepository.findAllByStudentId(studentId, pageable);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityPage.getContent()) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);
        }
        Long totalCount = studentCourseMarkEntityPage.getTotalElements();
        return new PageImpl<>(studentCourseMarkDetailDTOList, pageable, totalCount);

    }

    public PageImpl<StudentCourseMarkDetailDTO> paginationWithCourseId(Integer courseId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        PageImpl<StudentCourseMarkEntity> studentCourseMarkEntityPage = studentCourseMarkRepository.findAllByCourseId(courseId, pageable);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityPage.getContent()) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = studentService.byId(studentCourseMarkEntity.getStudentId());
            CourseDTO courseDTO = courseService.getCourseById(studentCourseMarkEntity.getCourseId());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);
        }
        Long totalCount = studentCourseMarkEntityPage.getTotalElements();
        return new PageImpl<>(studentCourseMarkDetailDTOList, pageable, totalCount);
    }

    public List<StudentCourseMarkDetailDTO> getMarkByStudentId(Integer studentId) {
        List<StudentCourseMarkEntity> studentCourseMarkEntityList = studentCourseMarkRepository.getMarkStudentIdInner(studentId);

        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity studentCourseMarkEntity : studentCourseMarkEntityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseService.getCourseById(studentCourseMarkEntity.getCourseId()).getId());
            courseDTO.setName(courseService.getCourseById(studentCourseMarkEntity.getCourseId()).getName());
            studentCourseMarkDetailDTO.setCourseDTO(courseDTO);
            studentCourseMarkDetailDTO.setMark(studentCourseMarkEntity.getMark());
            studentCourseMarkDetailDTO.setCreateDate(studentCourseMarkEntity.getCreatedDate());
            studentCourseMarkDetailDTO.setId(studentCourseMarkEntity.getId());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);
        }
        return studentCourseMarkDetailDTOList;
    }

    public List<?> getByMarkCourseId(Integer courseId) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.getMarkCourseId(courseId);
        List<StudentCourseMarkDetailDTO> studentCourseMarkDetailDTOList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDetailDTO studentCourseMarkDetailDTO = new StudentCourseMarkDetailDTO();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(entity.getStudentId());
            studentDTO.setName(studentService.get(entity.getStudentId()).getName());
            studentDTO.setSurname(studentService.get(entity.getStudentId()).getSurname());
            studentCourseMarkDetailDTO.setStudentDTO(studentDTO);
            studentCourseMarkDetailDTO.setId(entity.getId());
            studentCourseMarkDetailDTO.setCreateDate(entity.getCreatedDate());
            studentCourseMarkDetailDTO.setMark(entity.getMark());
            studentCourseMarkDetailDTOList.add(studentCourseMarkDetailDTO);

        }
        return studentCourseMarkDetailDTOList;

    }
}
