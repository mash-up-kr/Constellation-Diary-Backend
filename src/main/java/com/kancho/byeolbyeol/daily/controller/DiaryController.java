package com.kancho.byeolbyeol.daily.controller;

import com.kancho.byeolbyeol.common.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.daily.application.DiaryService;
import com.kancho.byeolbyeol.daily.dto.ReqModifyDiaryDto;
import com.kancho.byeolbyeol.daily.dto.ReqWriteDiaryDto;
import com.kancho.byeolbyeol.daily.dto.ResDiariesDto;
import com.kancho.byeolbyeol.daily.dto.ResDiaryDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;


    @ApiOperation(value = "일기 리스트")
    @ApiResponses({
            @ApiResponse(code = 201, message = "일기 리스트 보기 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4012 - Is Exceed Write Diary"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/diaries")
    public ResponseEntity<ResDiariesDto> getDiaries(@RequestParam("year") Integer year,
                                                    @RequestParam("month") Integer month) {

        if (year == null || month == null) {
            throw new RequestWornFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiaries(userInfo, year, month));
    }

    @ApiOperation(value = "일기 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "일기 작성 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4012 - Is Exceed Write Diary"),
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

    @ApiOperation(value = "일기 보기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 보기 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4010 - Not Found Diary, 4011 -Is Not The Writer"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/diaries/{diary-id:^[0-9]+$}")
    public ResponseEntity<ResDiaryDto> getDiary(
            @PathVariable("diary-id") Long diaryId) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiary(userInfo, diaryId));
    }

    @ApiOperation(value = "일기 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 수정 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4010 - Not Found Diary, 4011 -Is Not The Writer"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PatchMapping("/diaries/{diary-id:^[0-9]+$}")
    public ResponseEntity<ResDiaryDto> modifyDiary(
            @PathVariable("diary-id") Long diaryId,
            @RequestBody @Valid ReqModifyDiaryDto reqModifyDiaryDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(diaryService.modifyDiary(userInfo, diaryId, reqModifyDiaryDto));
    }

    @ApiOperation(value = "일기 삭제")
    @ApiResponses({
            @ApiResponse(code = 204, message = "일기 삭제 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4010 - Not Found Diary, 4011 -Is Not The Writer"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @DeleteMapping("/diaries/{diary-id:^[0-9]+$}")
    public ResponseEntity<Void> deleteDiary(
            @PathVariable("diary-id") Long diaryId) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        diaryService.deleteDiary(userInfo, diaryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
