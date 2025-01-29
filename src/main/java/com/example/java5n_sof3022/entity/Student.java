package com.example.java5n_sof3022.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter @Setter
//@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @Email(message = "Please enter a valid email address")
    //@Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email address")
    @Pattern(regexp = "^(.+)@(fpt\\.edu\\.vn)$", message = "Invalid FPT Poly email")
    private String email;

    @Pattern(regexp = "^(0)\\d{9}$", message = "Invalid phone number")
    private String phone;

    public Student() {
    }

    public Student(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
