package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Object getAllCategories() {

        return categoryRepository.findAll();
    }
}
