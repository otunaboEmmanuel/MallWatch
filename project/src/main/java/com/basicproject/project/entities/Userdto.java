package com.basicproject.project.entities;

import javax.persistence.*;

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
    @Column(name = "email")
    private String email;
    @Column(name = "lastName")
    private String lastName;
    @Column(name="password")
    private String password;
}
