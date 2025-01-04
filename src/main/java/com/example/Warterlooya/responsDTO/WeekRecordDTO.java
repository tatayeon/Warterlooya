package com.example.Warterlooya.responsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WeekRecordDTO {
    private LocalDate date; // 해당 날짜
    private int totalAmount; // 총 소비량
}
