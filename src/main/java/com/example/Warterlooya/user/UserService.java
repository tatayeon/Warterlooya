package com.example.Warterlooya.user;

import com.example.Warterlooya.enumration.RoleType;
import com.example.Warterlooya.security.custom.CustomUserInfoDto;
import com.example.Warterlooya.security.jwt.JwtUtil;
import com.example.Warterlooya.user.dto.LoginDTO;
import com.example.Warterlooya.user.dto.RequestRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(RequestRegisterDTO registerDTO){
        User user = new User(registerDTO.getUsername(), encoder.encode(registerDTO.getPassword()),RoleType.USER, registerDTO.getGender(), registerDTO.getWeight(), registerDTO.isOldAge(), registerDTO.getWeather());
        userRepository.save(user);

    }


    public String login(LoginDTO dto) {
        System.out.println(dto.getUsername() + " " + dto.getPassword());
        User user = userRepository.findByUsername(dto.getUsername());

        if(user == null){
            return "you can't use we service";
        }

        if(!encoder.matches(dto.getPassword(), user.getPassword())) {
            return "password error";
        }

        CustomUserInfoDto customUserInfoDto = new CustomUserInfoDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        return jwtUtil.createAccessToken(customUserInfoDto);

    }
}
