package com.example.dddk.repository;

import com.example.dddk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    User save(User user);
}