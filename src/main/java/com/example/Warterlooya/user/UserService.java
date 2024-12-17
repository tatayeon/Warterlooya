package com.example.Warterlooya.user;

import com.example.Warterlooya.enumration.RoleType;
import com.example.Warterlooya.user.dto.RequestRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void register(RequestRegisterDTO registerDTO){
        User user = new User(RoleType.USER, registerDTO.getGender(), registerDTO.getWeight(), registerDTO.isOldAge(), registerDTO.getWeather());
        userRepository.save(user);

    }

}
