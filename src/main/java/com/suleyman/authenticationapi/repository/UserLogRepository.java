package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog,Long> {
}
