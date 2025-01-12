package com.example.java5n_sof3022.controller;

import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        //// get data from DB
        //List<Student> students = studentService.getAllStudents();
        ////students.forEach(student -> System.out.println(student));
        //
        //// add to model
        //model.addAttribute("students", students);
        //
        //// return view name
        //return "views/students";

        return findPaginated(1, "name", "asc", model);
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

    @GetMapping("/students/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        // get student from the service
        Student student = studentService.getStudentById(id);

        // set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);

        return "views/update_student";
    }

    @PostMapping("/students/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {

        // update student to database
        studentService.updateStudent(student);

        return "redirect:/students";
    }

    @GetMapping("students/page/{pageNo}")
    public String findPaginated(@PathVariable ("pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 1;

        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Student> students = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("students", students);
        return "views/students";
    }
}
