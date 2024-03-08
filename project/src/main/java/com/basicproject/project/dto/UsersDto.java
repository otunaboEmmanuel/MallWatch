package com.basicproject.project.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String userId;
    private String initiatorId;
    private String profilePicture;
}
