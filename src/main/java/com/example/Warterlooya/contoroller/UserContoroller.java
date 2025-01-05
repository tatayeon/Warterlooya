package com.example.Warterlooya.contoroller;

import com.example.Warterlooya.service.UserService;
import com.example.Warterlooya.requstDTO.LoginDTO;
import com.example.Warterlooya.requstDTO.RequestRegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User API", description = "user관련 로그인 및 회원가입 등등")
public class UserContoroller {

     private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원 가입 기능", description = "회원가입에 사용되는 API")
    @ApiResponse(responseCode = "200", description = "회원 가입 성공", content = @Content(mediaType = "application/json"))
    public String register(@RequestBody RequestRegisterDTO registerDTO){
//        System.out.println(registerDTO.getGender());
        userService.register(registerDTO);

        String token = userService.login(new LoginDTO(registerDTO.getUsername(), registerDTO.getPassword()));
        insertToken(token);

        return token;
    }

    @PostMapping("/login")
    @Operation(summary = "회원 로그인 기능", description = "로그인에 사용되는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "로그인 실패", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> login(@RequestBody LoginDTO dto){
        System.out.println("dto = " + dto);
        String status = userService.login(dto);

        if(status.equals("user not found") || status.equals("password error")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(status);
        }

        insertToken(status);
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }


    @PostMapping("/logout")
    @Operation(summary = "로그아웃 기능", description = "로그아웃에 사용되는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로구아웃 성공", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 삭제
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully");
    }

    @GetMapping("/check/email/{email}")
    @Operation(summary = "아이디(이메일) 중복 검사", description = "회원 가입 시 아이디(이메일) 중복 검사 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능한 아이디", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "중복 아이디", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/check/username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable("username") String username){
        System.out.println("username = " + username);
        return userService.checkUserName(username);
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
