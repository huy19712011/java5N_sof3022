package com.example.java5n_sof3022.controller;

import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequiredArgsConstructor
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {

        // get data from DB
        List<Student> students = studentService.getAllStudents();
        students.forEach(student -> System.out.println(student));

        // add to model
        model.addAttribute("students", students);

        // return view name
        return "students";
    }
}
