package com.kancho.byeolbyeol.diary.controller;

import com.kancho.byeolbyeol.common.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.diary.application.DiaryService;
import com.kancho.byeolbyeol.diary.dto.ReqModifyDiaryDto;
import com.kancho.byeolbyeol.diary.dto.ReqWriteDiaryDto;
import com.kancho.byeolbyeol.diary.dto.ResDiaryDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;


    @ApiOperation(value = "일기 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "일기 작성 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PostMapping("/diaries")
    public ResponseEntity<Void> writeDiary(@RequestBody @Valid ReqWriteDiaryDto reqWriteDiaryDto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        diaryService.writeDiary(userInfo, reqWriteDiaryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "일기 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 수 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PatchMapping("/diaries")
    public ResponseEntity<ResDiaryDto> modifyDiary(@RequestBody @Valid ReqModifyDiaryDto reqModifyDiaryDto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.modifyDiary(userInfo, reqModifyDiaryDto));
    }

}
