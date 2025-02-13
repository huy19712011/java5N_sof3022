package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.User;
import com.example.java5n_sof3022.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(User user) {

        userRepository.save(user);
    }

    public User readUserAndAssociatedRoles(long id) {

        return userRepository.findById(id).get();
    }
}
