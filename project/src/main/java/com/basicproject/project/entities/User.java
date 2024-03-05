package com.basicproject.project.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name ="NEW_PROJECT") public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name="Last_Name")
    private String lastName;

    @Column
    private String fullName= firstName + lastName;
    @Column (name = "email",unique = true)
    private String email;
    @Column(name="password")
    //@JsonIgnore
    private String password;
    @Lob
   // @Type(type="org.hibernate.type.TextType")
    @Column(name="profile_picture")
    private String profilePicture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User(Long id, String firstName, String lastName, String fullName, String email, String password, String profilePicture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public User() {
    }
}
