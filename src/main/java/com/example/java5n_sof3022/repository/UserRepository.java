package com.example.java5n_sof3022.repository;

import com.example.java5n_sof3022.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
