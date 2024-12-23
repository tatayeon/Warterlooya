package com.example.Warterlooya.domain;

import com.example.Warterlooya.enumration.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String password;

    private RoleType role;

    private String gender;

    private int weight;

    private boolean oldAge;

    private String weather;


    public User(String username, String password, RoleType roleType, String gender, int weight, boolean oldAge, String weather) {
        this.username = username;
        this.password = password;
        this.role = roleType;
        this.gender = gender;
        this.weight = weight;
        this.oldAge = oldAge;
        this.weather = weather;
    }

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<DrinkRecord> records = new ArrayList<>();
}
