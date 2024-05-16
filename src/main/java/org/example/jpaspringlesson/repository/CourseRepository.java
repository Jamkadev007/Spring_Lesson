package org.example.jpaspringlesson.repository;

import org.example.jpaspringlesson.dto.CourseDTO;
import org.example.jpaspringlesson.entity.CourseEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface CourseRepository extends CrudRepository<CourseEntity , Integer> {

    Iterable<CourseEntity> findByName(String name);

    Iterable<CourseEntity> findByPrice(Integer price);

    Iterable<CourseEntity> findByDuration(Integer duration);

    Iterable<CourseEntity> findByPriceBetween(Integer from, Integer to);

    Iterable<CourseEntity> findByCreatedDateBetween(LocalDate from, LocalDate to);

    PageImpl<CourseEntity> findAll(Pageable pageable);

    PageImpl<CourseEntity> findAllByPrice(Double price,Pageable pageable);

    PageImpl<CourseEntity> findAllByPriceBetween(Double to, Double from, Pageable pageable);
}
