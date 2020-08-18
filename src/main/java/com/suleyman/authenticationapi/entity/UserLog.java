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
@Table(name = "USERLOG" ,schema = "USERS")
@SequenceGenerator(name = "users_log",schema = "USERS",sequenceName = "USERS.USERS_LOG_ID_SEQ",allocationSize = 1)
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="users_log")
    private Long id;

    private Long user_id;

    @Column(length = 1000)
    private String token;

    private String Ip;

    private LocalDateTime createdDate;
}
