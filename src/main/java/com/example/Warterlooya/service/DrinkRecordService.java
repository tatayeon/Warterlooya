package com.example.Warterlooya.service;

import com.example.Warterlooya.domain.DrinkRecord;
import com.example.Warterlooya.domain.User;
import com.example.Warterlooya.repository.DrinkRecordRepository;
import com.example.Warterlooya.repository.UserRepository;
import com.example.Warterlooya.requstDTO.AddRecordDTO;
import com.example.Warterlooya.responsDTO.OneDayRecordDTO;
import com.example.Warterlooya.responsDTO.WeekRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DrinkRecordService {

    private final UserRepository userRepository;
    private final DrinkRecordRepository recordRepository;

    @Transactional
    public String addRecord(AddRecordDTO dto, Long userId) throws ChangeSetPersister.NotFoundException {
        // 사용자를 데이터베이스에서 검색
        User user1 = userRepository.findById(userId)
                        .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        System.out.println(user1.getUsername());

        if(dto.getAmount() < 30){
            return "You don't have enough amount";
        }else{
            // Record 객체 생성
            DrinkRecord newRecord = DrinkRecord.builder()
                    .time(LocalDateTime.now())
                    .drinkType(dto.getDrinkType())
                    .amount(dto.getAmount())
                    .users(user1)
                    .build();

            // Record 저장
            recordRepository.save(newRecord);

            return "good";
        }
    }

    public List<OneDayRecordDTO> getAmountsByTimeOfDay(Long userId, LocalDate today) {
        // JPQL 실행
        List<Object[]> amountsByTimeOfDay = recordRepository.findAmountsByTimeOfDay(userId, today);

        // Object[] -> DTO 변환
        List<OneDayRecordDTO> collect = amountsByTimeOfDay.stream()
                .map(record -> new OneDayRecordDTO(
                        ((Number) record[0]).intValue(), // amount8
                        ((Number) record[1]).intValue(), // amount12
                        ((Number) record[2]).intValue(), // amount16
                        ((Number) record[3]).intValue()  // amount20
                ))
                .collect(Collectors.toList());
        return collect;
    }

    public List<WeekRecordDTO> getAmountsByWeek(Long userId, LocalDate today) {
        LocalDate weekStartDate = today.with(DayOfWeek.MONDAY);
        LocalDate weekEndDate = today.with(DayOfWeek.SUNDAY);

        List<Object[]> weeklyTotalAmounts = recordRepository.findWeeklyTotalAmounts(userId, weekStartDate, weekEndDate);

        return weeklyTotalAmounts.stream()
                .map(record -> new WeekRecordDTO(
                        ((java.sql.Date) record[0]).toLocalDate(), // java.sql.Date -> java.time.LocalDate 변환
                        ((Number) record[1]).intValue()
                ))
                .collect(Collectors.toList());
    }


}
