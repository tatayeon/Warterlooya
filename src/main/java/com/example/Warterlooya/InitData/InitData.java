package com.example.Warterlooya.InitData;

import com.example.Warterlooya.enumration.RoleType;
import com.example.Warterlooya.user.User;
import com.example.Warterlooya.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init(){
        User user1 = new User("나비", encoder.encode("1234"),RoleType.USER, "남자", 76, true, "흐림");
        User user2 = new User("고양이", encoder.encode("1234"),RoleType.USER, "여자", 56, false, "흐림");
        User user3 = new User("잠자리", encoder.encode("1234"),RoleType.USER, "남자", 87, false, "흐림");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

}
