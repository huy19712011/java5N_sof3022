package com.example.java5n_sof3022.repository;

import com.example.java5n_sof3022.entity.Student;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private EntityManager em;

    @Autowired
    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    public List<Student> getAllStudents() {

        return em.createQuery("from Student", Student.class).getResultList();
    }
}
