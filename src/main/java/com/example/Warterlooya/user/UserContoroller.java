package com.example.Warterlooya.user;

import com.example.Warterlooya.user.dto.LoginDTO;
import com.example.Warterlooya.user.dto.RequestRegisterDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserContoroller {

     private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RequestRegisterDTO registerDTO){
//        System.out.println(registerDTO.getGender());
        userService.register(registerDTO);

        String token = userService.login(new LoginDTO(registerDTO.getUsername(), registerDTO.getPassword()));
        insertToken(token);

        return token;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto){
        System.out.println("dto = " + dto);
        String status = userService.login(dto);

        if(status.equals("user not found") || status.equals("password error")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
        }

        insertToken(status);
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }

    public void insertToken(String token) {
        try {
            // 쿠키 값 UTF-8로 인코딩
            String cookieValue = URLEncoder.encode(token, "UTF-8");
            Cookie cookie = new Cookie("accessToken", cookieValue);

            cookie.setPath("/");
            cookie.setSecure(false); // 실제 배포 시 true로 설정
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
            cookie.setHttpOnly(true);

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            // 인코딩 실패 시 로깅 또는 예외 처리
            System.err.println("쿠키 인코딩 오류: " + e.getMessage());
        }
    }

    // 쿠키 값에 유효한 문자가 포함되어 있는지 확인하는 메서드
    private boolean isValidCookieValue(String value) {
        // 예시: 공백이 아닌 ASCII 32 이상인지 확인
        for (char c : value.toCharArray()) {
            if (c < 32 || c > 126) { // 허용할 ASCII 범위 설정
                return false;
            }
        }
        return true;
    }


}
