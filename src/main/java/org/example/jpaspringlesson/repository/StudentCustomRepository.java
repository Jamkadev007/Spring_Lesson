package org.example.jpaspringlesson.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.jpaspringlesson.dto.StudentFilterDTO;
import org.example.jpaspringlesson.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> filter(StudentFilterDTO filter) {
        StringBuilder query =
                new StringBuilder("From StudentEntity where visible = true ");

        if (filter.getName() != null) {
            query.append(" and name=:name ");
        }
        if (filter.getSurname() != null) {
            query.append(" and surname=:surname ");
        }
        if (filter.getId() != null) {
            query.append(" and id=:id ");
        }
        if (filter.getBirthday() != null) {
            query.append(" and birthdate=:birthdate ");
        }


        Query selectQuery = entityManager.createQuery(query.toString());

        if (filter.getName() != null) {
            selectQuery.setParameter("name", filter.getName());
        }
        if (filter.getSurname() != null) {
            selectQuery.setParameter("surname", filter.getSurname());
        }
        if (filter.getId() != null) {
            selectQuery.setParameter("id", filter.getId());
        }
        if (filter.getBirthday() != null) {
            selectQuery.setParameter("birthdate", filter.getBirthday());
        }

        List<StudentEntity> studentEntityList = selectQuery.getResultList();

        return studentEntityList;
    }

}
