package com.example.Warterlooya.responsDTO;

import lombok.Getter;

@Getter
public class OneDayRecordDTO {
    private int amount8;
    private int amount12;
    private int amount16;
    private int amount20;

    public OneDayRecordDTO(int amount8, int amount12, int amount16, int amount20) {
        this.amount8 = amount8;
        this.amount12 = amount12;
        this.amount16 = amount16;
        this.amount20 = amount20;
    }
}
