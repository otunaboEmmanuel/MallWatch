package com.basicproject.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emaildto {
    private String email;
    private Integer otp;
    private String password;
    private String repeatPassword;

}
