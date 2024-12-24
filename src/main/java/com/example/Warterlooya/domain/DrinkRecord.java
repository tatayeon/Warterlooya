package com.example.Warterlooya.domain;

import com.example.Warterlooya.enumration.DrinkType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class DrinkRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "record_id")
    private Long id;

    private LocalDateTime time;

    private DrinkType drinkType;

    private int amount;


    @Builder
    public DrinkRecord(LocalDateTime time, DrinkType drinkType, int amount, User users) {
        this.time = time;
        this.drinkType = drinkType;
        this.amount = amount;
        this.users = users;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

}
