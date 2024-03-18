package com.basicproject.project.entities;

import javax.persistence.*;

import com.basicproject.project.dto.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NEW_UPDATE")
public class Userdto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "lastName")
    private String lastName;
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_roles")
    private UserRoles roles;

    private String store;


}
