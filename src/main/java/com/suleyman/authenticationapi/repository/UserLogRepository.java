package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.UserTokenLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserTokenLog,Long> {
}
