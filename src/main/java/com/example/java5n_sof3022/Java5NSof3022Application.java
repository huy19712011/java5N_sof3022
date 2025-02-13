package com.example.java5n_sof3022;

import com.example.java5n_sof3022.entity.Role;
import com.example.java5n_sof3022.entity.User;
import com.example.java5n_sof3022.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Java5NSof3022Application implements CommandLineRunner {

    private final UserService userService;

    public Java5NSof3022Application(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Java5NSof3022Application.class, args);
        System.out.println("running...");

    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // users and roles
        User user = new User("user 1", "password 1", "phone 1", "email 1");
        Role role = new Role("admin");
        user.addRole(role);
        //role.addUser(user);
        userService.addUser(user);


        User user2 = new User("user 2", "password 2", "phone 2", "email 2");
        user2.addRole(role);
        userService.addUser(user2);


        User user3 = new User("user 3", "password 3", "phone 3", "email 3");
        Role role2 = new Role("user");
        user3.addRole(role2);
        user3.addRole(role);
        userService.addUser(user3);




    }
}
