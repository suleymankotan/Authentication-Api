package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
