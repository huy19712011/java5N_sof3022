package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.Role;
import com.example.java5n_sof3022.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role readRoleAndAssociatedUsers(long id) {

        return roleRepository.findById(id).get();
    }
}
