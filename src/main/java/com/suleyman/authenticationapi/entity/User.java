package com.suleyman.authenticationapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private Integer active;

    private Integer role;

    private String email;
}
