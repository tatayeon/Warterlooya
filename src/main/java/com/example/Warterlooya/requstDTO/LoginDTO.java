package com.example.Warterlooya.requstDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginDTO {

    @Schema(description = "사용자 이름", example = "test@naver.com")
    private String username;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
