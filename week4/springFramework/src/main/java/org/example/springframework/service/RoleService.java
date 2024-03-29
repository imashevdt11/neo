package org.example.springframework.service;

import lombok.RequiredArgsConstructor;
import org.example.springframework.entity.Role;
import org.example.springframework.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}