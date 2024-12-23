package com.example.Warterlooya.contoroller;

import com.example.Warterlooya.domain.User;
import com.example.Warterlooya.requstDTO.AddRecordDTO;
import com.example.Warterlooya.security.custom.CustomUserDetails;
import com.example.Warterlooya.service.DrinkRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordContorller {

    private final DrinkRecordService recordService;

    @PostMapping("/add")
    public String addRecord(@RequestBody AddRecordDTO dto, @AuthenticationPrincipal CustomUserDetails user) throws ChangeSetPersister.NotFoundException {
        System.out.println(user.getUsername());
        recordService.addRecord(dto, user.getUserId());
        return "good";
    }

}
