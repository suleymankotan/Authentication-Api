package com.suleyman.authenticationapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MailSend" ,schema = "mail")
public class MailSend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private String subject;

    private String toFrom;

    private String mailKey;

    private Long status;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
