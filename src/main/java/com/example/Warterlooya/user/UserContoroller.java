package com.example.Warterlooya.user;

import com.example.Warterlooya.user.dto.RequestRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserContoroller {

     private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RequestRegisterDTO registerDTO){
        System.out.println(registerDTO.getGender());
        userService.register(registerDTO);

        return "good";
    }

}
