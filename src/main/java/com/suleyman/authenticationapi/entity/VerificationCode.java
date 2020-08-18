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
@Table(name = "VerificationCode" ,schema = "USERS")
@SequenceGenerator(name = "VerificationCodedSeq",schema = "USERS",sequenceName = "USERS.Verification_Code_ID_SEQ",allocationSize = 1)
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "VerificationCodedSeq")
    private Long id;

    private Long userId;

    private String code;

    private LocalDateTime createdDate;

    private LocalDateTime expireDate;

    private Long active;

}
