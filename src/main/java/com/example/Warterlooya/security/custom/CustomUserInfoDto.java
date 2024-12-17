package com.example.Warterlooya.security.custom;

import com.example.Warterlooya.enumration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomUserInfoDto {
    private Long userId;

    private String username;

    private String password;

    private RoleType role;

}
