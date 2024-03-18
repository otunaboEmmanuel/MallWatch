package com.basicproject.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    private String email;
    private String password;

    private String initiatorUserId;
    private String userId;
    private String storeName;


}
