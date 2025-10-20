package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.Employee;
import com.example.java5n_sof3022.repository.EmployeeRepository;
import com.example.java5n_sof3022.specification.GenericSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EntityManager em;
    private final EmployeeRepository employeeRepository;

    // Combined Criteria and Specification demo
    public List<Employee> search(Map<String, Object> filters) {

        GenericSpecification<Employee> specification = new GenericSpecification<>(filters);
        return employeeRepository.findAll(specification);
    }

    // Criteria demo
    public List<Employee> searchCriteria(String department, Double minSalary) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        Predicate departmentPredicate = cb.equal(root.get("department"), department);
        Predicate salaryPredicate = cb.greaterThanOrEqualTo(root.get("salary"), minSalary);

        query.select(root).where(cb.and(departmentPredicate, salaryPredicate));
        //query.select(root).where(departmentPredicate, salaryPredicate);

        return em.createQuery(query).getResultList();

    }

    // JPA Specifications demo
    public Specification<Employee> hasDepartment(String department) {
        return (root, query, cb) -> cb.equal(root.get("department"), department);
    }

    public Specification<Employee> hasSalaryGreaterThan(Double minSalary) {
        return (root, query, cb) -> cb.greaterThan(root.get("salary"), minSalary);
    }

    public List<Employee> searchSpecification(String department, Double minSalary) {

        Specification<Employee> specification = Specification.where(hasDepartment(department).and(hasSalaryGreaterThan(minSalary)));
        return employeeRepository.findAll(specification);
        //return employeeRepository.findAll(hasDepartment(department).and(hasSalaryGreaterThan(minSalary)));
    }

}
