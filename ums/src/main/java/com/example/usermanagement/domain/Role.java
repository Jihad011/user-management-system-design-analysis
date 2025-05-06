package com.example.usermanagement.domain;

import java.util.UUID;

public class Role {
    private UUID id;
    private String roleName;

    public Role() {
        this.id = UUID.randomUUID();
    }

    public Role(String roleName) {
        this();
        this.roleName = roleName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
} 