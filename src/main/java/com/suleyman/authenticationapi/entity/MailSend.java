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
@SequenceGenerator(name = "mailIdSeq",schema = "mail",sequenceName = "mail.mail_SEND_ID_SEQ",allocationSize = 1)
public class MailSend {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "mailIdSeq")
    private Long id;

    private String text;

    private String subject;

    private String To_C;

    private String mailKey;

    private Integer status;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
