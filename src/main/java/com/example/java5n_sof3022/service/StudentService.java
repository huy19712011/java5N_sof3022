package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

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
}
