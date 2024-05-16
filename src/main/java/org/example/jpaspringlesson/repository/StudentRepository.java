package org.example.jpaspringlesson.repository;

import org.example.jpaspringlesson.dto.CourseDTO;
import org.example.jpaspringlesson.entity.StudentEntity;
import org.example.jpaspringlesson.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity , Integer> , PagingAndSortingRepository<StudentEntity,Integer> {

   List<StudentEntity> findByName(String name);

   Iterable<StudentEntity> findBySurname(String surname);

   Iterable<StudentEntity> findByLevel(Integer level);

   Iterable<StudentEntity> findByAge(Integer age);

   Iterable<StudentEntity> findByGender(Gender gender);

   Iterable<StudentEntity> findByCreatedDate(LocalDate createdDate);

   Iterable<StudentEntity> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

   List<StudentEntity> findByAgeIn(List<Integer> ageList);

   Page<StudentEntity> findByName(String name, Pageable pageable);

   Page<StudentEntity> findByLevel(Integer level, Pageable pageable);

   Page<StudentEntity> findByGender(Gender gender, Pageable pageable);



//   Page<StudentEntity> findByName(String name, Pageable pageable, Sort sort);

//   Page<StudentEntity> findByNameOrderByAge(String name, Pageable pageable);

}
