package com.example.Warterlooya.responsDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WeekRecordDTO {

    @Schema(description = "해당 날짜", example = "2025-01-05")
    private LocalDate date; // 해당 날짜

    @Schema(description = "총 소비량", example = "888")
    private int totalAmount; // 총 소비량
}
