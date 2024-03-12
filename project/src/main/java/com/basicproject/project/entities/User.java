package com.basicproject.project.entities;

import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name ="NEW_PROJECT")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Role")
    private String Role;
    @Column(name="Last_Name")
    private String lastName;

    @Column
    private String fullName= firstName + lastName;
    @Column (name = "email",unique = true)
    private String email;

    @Column (name = "otp",unique = true)
    private Integer otp;
    @Column(name="password")
    //@JsonIgnore
    private String password;
    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
    @Lob
   // @Type(type="org.hibernate.type.TextType")
    @Column(name="profile_picture")
    private String profilePicture;

    public User(Long id, String firstName, String role, String lastName, String fullName, String email, Integer otp, String password, ForgotPassword forgotPassword, String profilePicture) {
        this.id = id;
        this.firstName = firstName;
        Role = role;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
        this.otp = otp;
        this.password = password;
        this.forgotPassword = forgotPassword;
        this.profilePicture = profilePicture;
    }

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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ForgotPassword getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", Role='" + Role + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", otp=" + otp +
                ", password='" + password + '\'' +
                ", forgotPassword=" + forgotPassword +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }

    public User() {
    }
}
