package com.example.java5n_sof3022;

import com.example.java5n_sof3022.entity.Role;
import com.example.java5n_sof3022.entity.User;
import com.example.java5n_sof3022.service.RoleService;
import com.example.java5n_sof3022.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class Java5NSof3022ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void testReadUserAndAssociatedRoles() {

        User user = userService.readUserAndAssociatedRoles(3L);
        System.out.println("OKi: " + user.getRoles());
    }

    @Test
    @Transactional
    void testReadRoleAndAssociatedUsers() {

        Role role = roleService.readRoleAndAssociatedUsers(1L);
        System.out.println("OKi: " + role.getUsers());
    }

}
