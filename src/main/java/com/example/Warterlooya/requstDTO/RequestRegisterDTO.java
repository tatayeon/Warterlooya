package com.example.Warterlooya.requstDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RequestRegisterDTO {

    @Schema(description = "사용자 이름", example = "쭈꾸미")
    private String username;

    @Schema(description = "사용자 비밀번호", example = "1234")
    private String password;

    @Schema(description = "사용자 성별", example = "남자")
    private String gender;

    @Schema(description = "사용자 몸무게", example = "43")
    private int weight;

    @Schema(description = "사용자 나이 응답", example = "false")
    private boolean oldAge;

    @Schema(description = "사용자가 선택한 날씨", example = "흐림")
    private String weather;

}
