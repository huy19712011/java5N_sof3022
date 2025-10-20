package com.example.java5n_sof3022.controller;

import com.example.java5n_sof3022.entity.Employee;
import com.example.java5n_sof3022.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Combine Criteria and Specification demo
    // GET /employees/search?name=al&department=IT&minSalary=5000
    @GetMapping("/search")
    public List<Employee> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary
    ) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("name", name);
        filters.put("department", department);
        filters.put("minSalary", minSalary);
        filters.put("maxSalary", maxSalary);

        return employeeService.search(filters);
    }

    // Criteria demo
    // GET /employees/searchCriteria?name=al&department=IT&minSalary=5000
    @GetMapping("/searchCriteria")
    public List<Employee> searchCriteria(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double minSalary
    ) {

        return employeeService.searchCriteria(department, minSalary);
    }

    // Criteria demo
    // GET /employees/searchCriteria?name=al&department=IT&minSalary=5000
    @GetMapping("/searchSpecification")
    public List<Employee> searchSpecification(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double minSalary
    ) {

        return employeeService.searchSpecification(department, minSalary);
    }


}
