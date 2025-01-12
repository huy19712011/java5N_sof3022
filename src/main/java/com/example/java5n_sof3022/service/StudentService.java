package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.repository.StudentRepository;
import com.example.java5n_sof3022.repository.StudentRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    /*
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {

        return studentRepository.getAllStudents();
    }

    public void saveStudent(Student student) {

        this.studentRepository.saveStudent(student);
    }

    public void deleteStudentById(long id) {

        this.studentRepository.deleteStudentById(id);
    }

    public Student getStudentById(long id) {

            return this.studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student) {

        this.studentRepository.updateStudent(student);
    }
    */

    // Using JPA Repository (StudentRepositoryV2)
    StudentRepositoryV2 studentRepositoryV2;
    @Autowired
    public StudentService(StudentRepositoryV2 studentRepositoryV2) {
        this.studentRepositoryV2 = studentRepositoryV2;
    }

    public List<Student> getAllStudents() {

        return studentRepositoryV2.findAll();
    }

    public void saveStudent(Student student) {

        this.studentRepositoryV2.save(student);
    }

    public void deleteStudentById(long id) {

        this.studentRepositoryV2.deleteById(id);
    }

    public Student getStudentById(long id) {

        return this.studentRepositoryV2.findById(id).get();
    }

    public void updateStudent(Student student) {

        this.studentRepositoryV2.save(student);
    }

}
