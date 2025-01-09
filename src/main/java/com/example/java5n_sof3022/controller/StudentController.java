package com.example.java5n_sof3022.controller;

import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
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
        //students.forEach(student -> System.out.println(student));

        // add to model
        model.addAttribute("students", students);

        // return view name
        return "views/students";
    }

    @GetMapping("/students/showNewStudentForm")
    public String showNewStudentForm(Model model) {

        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);

        return "views/new_student";
    }

    @PostMapping("/students/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {

        // save student to database
        studentService.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping("/students/deleteStudent/{id}")

    public String deleteStudent(@PathVariable("id") long id) {

        // delete student by id
        studentService.deleteStudentById(id);

        return "redirect:/students";
    }
}
