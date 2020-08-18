package com.suleyman.authenticationapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS" ,schema = "USERS")
@SequenceGenerator(name = "usersIdSeq",schema = "USERS",sequenceName = "USERS.USERS_ID_SEQ",allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usersIdSeq")
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private Integer active;

    private Integer role;

    private String email;
}
