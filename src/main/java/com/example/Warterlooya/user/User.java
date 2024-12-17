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

    private String Gender;

    private String weight;

    private boolean oldAge;

    private String weather;

}
