package com.example.usermanagement.application.interfaces;

import com.example.usermanagement.domain.Role;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findById(UUID id);
    List<Role> findAll();
    void deleteById(UUID id);
    Optional<Role> findByRoleName(String roleName);
} 