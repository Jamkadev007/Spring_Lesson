package org.example.jpaspringlesson.service;

import org.example.jpaspringlesson.dto.CourseDTO;
import org.example.jpaspringlesson.entity.CourseEntity;
import org.example.jpaspringlesson.entity.StudentEntity;
import org.example.jpaspringlesson.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO courseDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseDTO.getName());
        courseEntity.setDuration(courseDTO.getDuration());
        courseEntity.setPrice(courseDTO.getPrice());
        courseRepository.save(courseEntity);
        courseDTO.setId(courseEntity.getId());
        courseDTO.setCreationDate(courseEntity.getCreatedDate());
        return courseDTO;
    }


    public CourseDTO getCourseById(Integer id) {

        CourseEntity courseEntity = get(id);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(courseEntity.getId());
        courseDTO.setName(courseEntity.getName());
        courseDTO.setDuration(courseEntity.getDuration());
        courseDTO.setPrice(courseEntity.getPrice());
        courseDTO.setCreationDate(courseEntity.getCreatedDate());
        return courseDTO;

    }

    public CourseEntity get(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Course not found");
        });
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseEntity> courseEntities = (List<CourseEntity>) courseRepository.findAll();
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntities) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);

        }
        return courseDTOs;

    }

    public Boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity courseEntity = get(id);
        if (courseEntity == null) {
            throw new IllegalArgumentException("Course not found");
        }
        courseEntity.setName(courseDTO.getName());
        courseEntity.setDuration(courseDTO.getDuration());
        courseEntity.setPrice(courseDTO.getPrice());
        courseEntity.setCreatedDate(courseDTO.getCreationDate());
        courseRepository.save(courseEntity);

        return true;
    }

    public boolean delete(Integer id) {
        CourseEntity courseEntity = get(id);
        if (courseEntity == null) {
            throw new IllegalArgumentException("Course not found");
        }
        courseRepository.delete(courseEntity);
        return true;

    }

    public List<CourseDTO> getByname(String name) {
        Iterable<CourseEntity> courseEntityIterable = courseRepository.findByName(name);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;

    }

    public List<CourseDTO> getByPrice(Integer price) {
        Iterable<CourseEntity> courseEntityIterable = courseRepository.findByPrice(price);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;

    }

    public List<CourseDTO> getByDuration(Integer duration) {
        Iterable<CourseEntity> courseEntityIterable = courseRepository.findByDuration(duration);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;
    }

    public List<CourseDTO> getByBetweenPrice(Integer from, Integer to) {
        Iterable<CourseEntity> courseEntityIterable = courseRepository.findByPriceBetween(from,to);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;
    }

    public List<CourseDTO> getBeweenCreatedDate(LocalDate from, LocalDate to) {

        Iterable<CourseEntity> courseEntityIterable = courseRepository.findByCreatedDateBetween(from,to);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;

    }

    public PageImpl<CourseDTO> pagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        PageImpl<CourseEntity> courseEntityIterable = courseRepository.findAll(pageable);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityIterable) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);
        }
        Long totalCount = courseEntityIterable.getTotalElements();

        return new PageImpl<>(courseDTOs, pageable, totalCount);
    }

    public PageImpl<CourseDTO> paginationByPrice(Double price, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        PageImpl<CourseEntity> courseEntityPage = courseRepository.findAllByPrice(price,pageable);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntityPage) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);

        }
        Long totalCount = courseEntityPage.getTotalElements();
        return new PageImpl<>(courseDTOs, pageable, totalCount);
    }

    public PageImpl<CourseDTO> paginationByTwoPrice(Double to, Double from, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        PageImpl<CourseEntity> courseEntities = courseRepository.findAllByPriceBetween(to,from,pageable);
        List<CourseDTO> courseDTOs = new LinkedList<>();
        for (CourseEntity courseEntity : courseEntities) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseEntity.getId());
            courseDTO.setName(courseEntity.getName());
            courseDTO.setDuration(courseEntity.getDuration());
            courseDTO.setPrice(courseEntity.getPrice());
            courseDTO.setCreationDate(courseEntity.getCreatedDate());
            courseDTOs.add(courseDTO);

        }
        Long totalCount = courseEntities.getTotalElements();
        return new PageImpl<>(courseDTOs, pageable, totalCount);
    }
}
