package com.example.Warterlooya.user;

import com.example.Warterlooya.enumration.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private RoleType role;

    private String gender;

    private int weight;

    private boolean oldAge;

    private String weather;

    public User(RoleType roleType, String gender, int weight, boolean oldAge, String weather) {
        this.role = roleType;
        this.gender = gender;
        this.weight = weight;
        this.oldAge = oldAge;
        this.weather = weather;
    }
}
