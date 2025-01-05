package com.example.Warterlooya.contoroller;

import com.example.Warterlooya.domain.User;
import com.example.Warterlooya.requstDTO.AddRecordDTO;
import com.example.Warterlooya.responsDTO.OneDayRecordDTO;
import com.example.Warterlooya.responsDTO.WeekRecordDTO;
import com.example.Warterlooya.security.custom.CustomUserDetails;
import com.example.Warterlooya.service.DrinkRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.http.WebSocket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
@Tag(name = "Record API", description = "모든 기록에 관한 API들")
public class RecordContorller {

    private final DrinkRecordService recordService;

    @PostMapping("/add")
    @Operation(summary = "기록 추가 기능", description = "사용자가 기록을 추가할 때 사용되는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "기록 실패", content = @Content(mediaType = "application/json"))
    })
    public String addRecord(@RequestBody AddRecordDTO dto, @AuthenticationPrincipal CustomUserDetails user) throws ChangeSetPersister.NotFoundException {
        System.out.println(user.getUsername());
        return recordService.addRecord(dto, user.getUserId());
    }

    @GetMapping("/view/day")
    @Operation(summary = "하루치 기록 열람", description = "사용자의 하루 기록을 볼 수 있는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "열람 성공", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<OneDayRecordDTO>> viewRecord(@AuthenticationPrincipal CustomUserDetails user) {
        LocalDate today = LocalDate.now(); // 오늘 날짜
        Long userId = user.getUserId();
        List<OneDayRecordDTO> result = recordService.getAmountsByTimeOfDay(userId, today);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/view/week")
    @Operation(summary = "일주일치 기록 열람", description = "사용자의 일주일치 기록을 볼 수 있는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "열람 성공", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<WeekRecordDTO>> weekRecord(@AuthenticationPrincipal CustomUserDetails user) {
        // 현재 날짜 및 사용자 ID 가져오기
        LocalDate today = LocalDate.now();
        Long userId = user.getUserId();

        // 주간 데이터 조회
        List<WeekRecordDTO> result = recordService.getAmountsByWeek(userId, today);

        // 결과 반환
        return ResponseEntity.ok(result);
    }


}
