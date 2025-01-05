package com.example.Warterlooya.responsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class OneDayRecordDTO {

    @Schema(description = "8시 기록", example = "150")
    private int amount8;

    @Schema(description = "12시 기록", example = "180")
    private int amount12;

    @Schema(description = "16시 기록", example = "210")
    private int amount16;

    @Schema(description = "20시 기록", example = "300")
    private int amount20;

    public OneDayRecordDTO(int amount8, int amount12, int amount16, int amount20) {
        this.amount8 = amount8;
        this.amount12 = amount12;
        this.amount16 = amount16;
        this.amount20 = amount20;
    }
}
