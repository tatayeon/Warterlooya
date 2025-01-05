package com.example.Warterlooya.requstDTO;

import com.example.Warterlooya.enumration.DrinkType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AddRecordDTO {

    @Schema(description = "기록할 물의 타입", example = "water")
    private DrinkType drinkType;

    @Schema(description = "기록할 물의 양", example = "150")
    private int amount;

}
