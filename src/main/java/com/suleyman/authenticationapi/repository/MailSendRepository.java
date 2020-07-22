package com.suleyman.authenticationapi.repository;

import com.suleyman.authenticationapi.entity.MailSend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailSendRepository extends JpaRepository<MailSend,Long> {
}
