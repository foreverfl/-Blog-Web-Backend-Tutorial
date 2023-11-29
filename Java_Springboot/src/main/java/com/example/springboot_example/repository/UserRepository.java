package com.example.springboot_example.repository;

import com.example.springboot_example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
