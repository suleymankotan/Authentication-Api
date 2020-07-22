package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getByUsername(String username);
    Optional<User> getByEmail(String email);
    User getById(Long id);
}
