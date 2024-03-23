package com.basicproject.project.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Data
public class ForgotPassword
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer fpid;
    @Column(nullable = false)
    private Integer otp;
    @Column(nullable = false)
    private Date expirationTime;
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId")
//    private Userdto userdto;
     @ManyToOne
     @JoinColumn(name = "users_id", referencedColumnName = "userId")
    private Userdto userdto;



}
