package com.example.Warterlooya.service;

import com.example.Warterlooya.domain.DrinkRecord;
import com.example.Warterlooya.domain.User;
import com.example.Warterlooya.repository.DrinkRecordRepository;
import com.example.Warterlooya.repository.UserRepository;
import com.example.Warterlooya.requstDTO.AddRecordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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
