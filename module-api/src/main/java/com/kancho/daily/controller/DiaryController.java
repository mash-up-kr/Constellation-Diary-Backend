package com.kancho.daily.controller;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.RequestWrongFieldException;
import com.kancho.common.user_context.ThreadContext;
import com.kancho.common.user_context.UserInfo;
import com.kancho.daily.application.DiaryService;
import com.kancho.daily.dto.request.ReqDiariesDto;
import com.kancho.daily.dto.request.ReqModifyDiaryDto;
import com.kancho.daily.dto.request.ReqWriteDiaryDto;
import com.kancho.daily.dto.response.ResCountDiariesDto;
import com.kancho.daily.dto.response.ResDiariesDto;
import com.kancho.daily.dto.response.ResDiaryDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(description = "일기 관련 API")
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value = "일기 리스트 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 리스트 요청 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
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
                                                    @RequestParam("month") Integer month,
                                                    @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        if (year == null || month == null) {
            throw new RequestWrongFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiaries(userInfo, year, month, reqTimeZone));
    }

    @ApiOperation(value = "일기 작성 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 201, message = "일기 작성 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 403, message = "4302 - Is Exceed Write Diary"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/diaries")
    public ResponseEntity<ResDiaryDto> writeDiary(@RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
                                           @RequestBody @Valid ReqWriteDiaryDto reqWriteDiaryDto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(diaryService.writeDiary(userInfo, reqWriteDiaryDto, reqTimeZone));
    }

    @ApiOperation(value = "일기 보기 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 보기 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4008 - Not Found Diary"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 403, message = "4301 - Is Not The Writer"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/diaries/{diary-id:^[0-9]+$}")
    public ResponseEntity<ResDiaryDto> getDiary(
            @PathVariable("diary-id") Long diaryId,
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.getDiary(userInfo, diaryId, reqTimeZone));
    }

    @ApiOperation(value = "일기 수정 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 수정 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4008 - Not Found Diary"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 403, message = "4301 - Is Not The Writer"),
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
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
            @RequestBody @Valid ReqModifyDiaryDto reqModifyDiaryDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(diaryService.modifyDiary(userInfo, diaryId, reqModifyDiaryDto, reqTimeZone));
    }

    @ApiOperation(value = "일기 삭제")
    @ApiResponses({
            @ApiResponse(code = 204, message = "일기 삭제 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4008 - Not Found Diary"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 403, message = "4301 - Is Not The Writer"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/diaries/{diary-id:^[0-9]+$}")
    public ResponseEntity<Void> deleteDiary(
            @PathVariable("diary-id") Long diaryId) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        diaryService.deleteDiary(userInfo, diaryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ApiOperation(value = "다수의 일기 삭제")
    @ApiResponses({
            @ApiResponse(code = 204, message = "다수의 일기 삭제 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Is Empty Diaries"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/diaries")
    public ResponseEntity<Void> deleteDiaries(@RequestBody ReqDiariesDto reqDiariesDto,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        diaryService.deleteDiaries(userInfo, reqDiariesDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ApiOperation(value = "연도의 달별로 일기 카운트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "연도의 달별로 일기 카운트 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/diaries/count")
    public ResponseEntity<ResCountDiariesDto> getCountDiaries(
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
            @RequestParam("year") Integer year) {

        if (year == null) {
            throw new RequestWrongFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(diaryService.countDiaries(userInfo, reqTimeZone, year));
    }

}
