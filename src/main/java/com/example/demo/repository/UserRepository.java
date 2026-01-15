package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // 0 1
    User findByUsernameAndPassword(String username, String password); // 0 1
    // Optional<User> findByUsernameAndPassword(String username, String password); // 0 1
    // List<User> findByName(String name); // 0 1 2~~?
}
