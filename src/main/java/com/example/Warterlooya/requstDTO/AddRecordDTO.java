package com.example.Warterlooya.requstDTO;

import com.example.Warterlooya.enumration.DrinkType;
import lombok.Getter;

@Getter
public class AddRecordDTO {

    private DrinkType drinkType;

    private int amount;

}
