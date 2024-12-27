package com.example.Warterlooya.InitData;

import com.example.Warterlooya.domain.DrinkRecord;
import com.example.Warterlooya.enumration.DrinkType;
import com.example.Warterlooya.enumration.RoleType;
import com.example.Warterlooya.domain.User;
import com.example.Warterlooya.repository.DrinkRecordRepository;
import com.example.Warterlooya.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final DrinkRecordRepository drinkRecordRepository;

    @PostConstruct
    public void init(){
        User user1 = new User("나비", encoder.encode("1234"),RoleType.USER, "남자", 76, true, "흐림");
        User user2 = new User("고양이", encoder.encode("1234"),RoleType.USER, "여자", 56, false, "흐림");
        User user3 = new User("잠자리", encoder.encode("1234"),RoleType.USER, "남자", 87, false, "흐림");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        DrinkRecord d1 = DrinkRecord.builder()
                .time(LocalDateTime.now())
                .drinkType(DrinkType.valueOf("WATER"))
                .amount(150)
                .users(user1)
                .build();
        drinkRecordRepository.save(d1);

        DrinkRecord d2 = DrinkRecord.builder()
                .time(LocalDateTime.now().plusHours(10))
                .drinkType(DrinkType.valueOf("WATER"))
                .amount(150)
                .users(user1)
                .build();
        drinkRecordRepository.save(d2);
        DrinkRecord d3 = DrinkRecord.builder()
                .time(LocalDateTime.now().plusHours(13))
                .drinkType(DrinkType.valueOf("WATER"))
                .amount(150)
                .users(user1)
                .build();
        drinkRecordRepository.save(d3);

        DrinkRecord d4 = DrinkRecord.builder()
                .time(LocalDateTime.now().plusHours(13))
                .drinkType(DrinkType.valueOf("WATER"))
                .amount(150)
                .users(user1)
                .build();
        drinkRecordRepository.save(d4);

        DrinkRecord d5 = DrinkRecord.builder()
                .time(LocalDateTime.now().plusHours(13))
                .drinkType(DrinkType.valueOf("WATER"))
                .amount(150)
                .users(user1)
                .build();
        drinkRecordRepository.save(d5);

    }

}
