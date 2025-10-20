package com.example.java5n_sof3022.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GenericSpecification <T> implements Specification<T> {

    private final Map<String, Object> filters;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) continue;

            switch (key) {
                case "minSalary" -> predicates.add(
                        cb.greaterThanOrEqualTo(root.get("salary"), ((Number) value).doubleValue())
                );
                case "maxSalary" -> predicates.add(
                        cb.lessThanOrEqualTo(root.get("salary"), ((Number) value).doubleValue())
                );
                case "name" -> predicates.add(
                        cb.like(cb.lower(root.get("name")), "%" + value.toString().toLowerCase() + "%")
                );
                default -> predicates.add(cb.equal(root.get(key), value));
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

 }
