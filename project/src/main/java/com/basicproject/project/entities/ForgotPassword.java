package com.basicproject.project.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ForgotPassword
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer fpid;
    @Column(nullable = false)
    private Integer otp;
    @Column(nullable = false)
    private Date expirationTime;
    @OneToOne
    private Userdto userdto;



    public Integer getFpid() {
        return fpid;
    }

    public void setFpid(Integer fpid) {
        this.fpid = fpid;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Userdto getUserdto() {
        return userdto;
    }

    public void setUserdto(Userdto userdto) {
        this.userdto = userdto;
    }

    @Override
    public String toString() {
        return "ForgotPassword{" +
                "fpid=" + fpid +
                ", otp='" + otp + '\'' +
                ", expirationTime=" + expirationTime +
                ", userdto=" + userdto +
                '}';
    }
}
