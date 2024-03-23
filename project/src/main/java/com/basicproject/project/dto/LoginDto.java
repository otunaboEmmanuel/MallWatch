package com.basicproject.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    private String email;
    private String password;
    private Integer prouctId;
    private String initiatorUserId;
    private String userId;
    private String storeName;
    private String cartId;

}


