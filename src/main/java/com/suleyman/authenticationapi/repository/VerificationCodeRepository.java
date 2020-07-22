package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    VerificationCode getByCode(String code);
}
