package org.example.jpaspringlesson.service;


import org.example.jpaspringlesson.dto.StudentDTO;
import org.example.jpaspringlesson.entity.StudentEntity;
import org.example.jpaspringlesson.enums.Gender;
import org.example.jpaspringlesson.repository.StudentCustomRepository;
import org.example.jpaspringlesson.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCustomRepository customRepository;
    
    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;

    }

    public StudentDTO byId(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setAge(entity.getAge());
        dto.setCreated_date(entity.getCreatedDate());


        return dto;
    }


    public StudentDTO create(StudentDTO studentDTO) {


        StudentEntity entity = new StudentEntity();
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setAge(studentDTO.getAge());
        entity.setLevel(studentDTO.getLevel());
        entity.setGender(studentDTO.getGender());
        studentRepository.save(entity);
        studentDTO.setId(entity.getId());

        return studentDTO;
    }

    public Boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        studentRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
//        studentRepository.deleteById(id);
        StudentEntity student = get(id);
        studentRepository.delete(student);
        return true;
    }


    public StudentEntity get(Integer id) {
        /*Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        return optional.get();*/

        return studentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Student not found");
        });
    }

    public List<StudentDTO> getByName(String name) {
        Iterable<StudentEntity> iterable = studentRepository.findByName(name);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }


    public List<StudentDTO> getBySurname(String surname) {
        Iterable<StudentEntity> iterable = studentRepository.findBySurname(surname);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByLevel(Integer level) {

        Iterable<StudentEntity> iterable = studentRepository.findByLevel(level);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByAge(Integer age) {
        Iterable<StudentEntity> iterable = studentRepository.findByAge(age);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByGender(String gender) {
        Iterable<StudentEntity> iterable = studentRepository.findByGender(Gender.valueOf(gender));
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByDate(LocalDate date) {

        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDate(date);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByBetween(LocalDate from, LocalDate to) {
        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDateBetween(from,to);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : iterable) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    public List<StudentDTO> getByAgeIn(List<Integer> ageList) {
        List<StudentEntity> studentEntityList = studentRepository.findByAgeIn(ageList);
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : studentEntityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public PageImpl<StudentDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);
        }

        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }

    public PageImpl<StudentDTO> paginationWithName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("age").descending());

        Page<StudentEntity> pageObj = studentRepository.findByName(name, pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);
        }

        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }


    public PageImpl<StudentDTO> paginationWithLevel(Integer level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<StudentEntity> pageObj = studentRepository.findByLevel(level,pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : pageObj.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);
        }

        Long totalCount = pageObj.getTotalElements();

        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }

    public PageImpl<StudentDTO> paginationWithGender(Gender  gender, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<StudentEntity> page1 = studentRepository.findByGender(gender,pageable);

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : page1.getContent()) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setAge(entity.getAge());
            dto.setCreated_date(entity.getCreatedDate());
            dtoList.add(dto);

        }
        Long totalCount = page1.getTotalElements();
        return new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
    }
}
