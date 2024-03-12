package com.basicproject.project.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Builder
public class ForgotPassword
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer fpid;
    @Column(nullable = false)
    private String otp;
    @Column(nullable = false)
    private Date expirationTime;
    @OneToOne
    private User user;

    public ForgotPassword(String otp, Date expirationTime, User user) {
        this.otp = otp;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ForgotPassword() {
    }

    @Override
    public String toString() {
        return "ForgotPassword{" +
                "fpid=" + fpid +
                ", otp=" + otp +
                ", expirationTime=" + expirationTime +
                ", user=" + user +
                '}';
    }
}
