package org.example.jpaspringlesson.repository;

import jakarta.persistence.NamedQueries;
import org.example.jpaspringlesson.entity.StudentCourseMarkEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity , Integer> {

    Iterable<StudentCourseMarkEntity> findByCreatedDate(LocalDate createdDate);

    Iterable<StudentCourseMarkEntity> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

    Iterable<StudentCourseMarkEntity> findByOrderByCreatedDateDesc();

    Iterable<StudentCourseMarkEntity> findByStudentIdOrderByCreatedDateDesc(Integer studentId);

    Iterable<StudentCourseMarkEntity> findTop3ByStudentIdOrderByMarkDesc(Integer studentId);


    StudentCourseMarkEntity findTop1ByStudentIdOrderByCreatedDateAsc(Integer studentId);

    StudentCourseMarkEntity findTop1ByStudentIdAndCourseIdOrderByCreatedDateAsc(Integer studentId,Integer courseId);

    StudentCourseMarkEntity findTop1ByStudentIdAndCourseIdOrderByMarkDesc(Integer studentId, Integer courseId);

    @Query("select avg(mark) from StudentCourseMarkEntity where studentId = ?1")
    Integer avg(Integer studentId);

    @Query("select avg(mark) from StudentCourseMarkEntity where studentId = :studentId and courseId = :courseId")
    Integer avgCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);


    @Query("select count(*) from StudentCourseMarkEntity where studentId = ?1 and mark > ?2")
    Integer getThanMark(Integer studentId , Integer mark);

    @Query("select max(mark) from StudentCourseMarkEntity where courseId = ?1")
    Integer getGreatMarkByCourse(Integer courseId);

    @Query("select avg(mark) from StudentCourseMarkEntity where courseId = ?1")
    Integer getAvgByCourse(Integer courseId);
    
    @Query("select count(*) from StudentCourseMarkEntity where courseId = ?1")
    Integer getCountByCourse(Integer courseId);

    PageImpl<StudentCourseMarkEntity> findAll(Pageable pageable);

    PageImpl<StudentCourseMarkEntity> findAllByStudentId(Integer studentId , Pageable  pageable);


    PageImpl<StudentCourseMarkEntity> findAllByCourseId(Integer courseId, Pageable pageable);

    @Query(value = "select stm.id, stm.course_id, stm.created_date, stm.mark,c.name,stm.student_id from student_course_mark as stm inner join course as c on c.id = stm.course_id where stm.student_id = ?1 ",nativeQuery = true)
    List<StudentCourseMarkEntity> getMarkStudentIdInner(Integer studentId);

    @Query(value = "select stm.id, stm.course_id, " +
            "stm.created_date, stm.mark,stm.student_id" +
            " from student_course_mark as stm inner join student as s on s.id = stm.student_id " +
            "where stm.course_id = ?1 ",nativeQuery = true)
    List<StudentCourseMarkEntity> getMarkCourseId(Integer courseId);

}

