package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity(user.getId(), user.getName(), user.getEmail());
        entity.setRoles(user.getRoles().stream()
                .map(role -> new RoleJpaEntity(role.getId(), role.getRoleName()))
                .collect(Collectors.toSet()));
        
        UserJpaEntity savedEntity = userJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::mapToDomain);
    }

    private User mapToDomain(UserJpaEntity entity) {
        User user = new User(entity.getName(), entity.getEmail());
        user.setId(entity.getId());
        entity.getRoles().forEach(roleEntity -> {
            Role role = new Role(roleEntity.getRoleName());
            role.setId(roleEntity.getId());
            user.addRole(role);
        });
        return user;
    }
} 