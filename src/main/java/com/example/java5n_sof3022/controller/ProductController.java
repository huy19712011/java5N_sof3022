package com.example.java5n_sof3022.controller;

import com.example.java5n_sof3022.entity.Product;
import com.example.java5n_sof3022.entity.Student;
import com.example.java5n_sof3022.service.CategoryService;
import com.example.java5n_sof3022.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {

        // get data from DB
        List<Product> products = productService.getAllProducts();

        // add to model
        model.addAttribute("products", products);

        // return view name
        return "views/products";

    }

    @GetMapping("/products/showNewProductForm")
    public String showNewProductForm(Model model) {

        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/new_product";
    }

    @PostMapping("/products/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());
            return "views/new_product";
        }

        /*
        System.out.println("Info");
        System.out.println(product.getCategory().getName());
        System.out.println(product.getCategory().getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println("End Info");
        */

        // save student to database
        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/products/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id) {

        // delete product by id
        productService.deleteProductById(id);

        return "redirect:/products";
    }

    @GetMapping("/products/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        // get product from the database
        Product product = productService.getProductById(id);

        // set product as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/update_product";
    }

    @PostMapping("/products/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());
            return "views/update_product";
        }

        // update product to database
        productService.updateProduct(product);

        return "redirect:/products";
    }


}
