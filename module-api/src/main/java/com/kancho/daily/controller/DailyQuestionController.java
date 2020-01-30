package com.kancho.daily.controller;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.user_context.ThreadContext;
import com.kancho.common.user_context.UserInfo;
import com.kancho.common.util.TimeCalculate;
import com.kancho.daily.application.DailyQuestionService;
import com.kancho.daily.dto.ResDailyQuestionDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@Api(description = "오늘의 질문 관련 API")
public class DailyQuestionController {
    private final DailyQuestionService dailyQuestionService;

    @ApiOperation(value = "데일리 질문 - question 필드에 일기 제목 or 질문, existDiary - 일기 존재 여부, 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "데일리 질문 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca"),
            @ApiImplicitParam(name = "date", value = "Request Date",
                    required = true, dataType = "string", paramType = "query",
                    defaultValue = "yyyy-MM-ddTHH:mm:ss.SSSZ")
    })
    @GetMapping("/daily-questions")
    public ResponseEntity<ResDailyQuestionDto> getDailyQuestions(@RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
                                                                 @RequestParam(value = "date") String date) {
        Date requestDate = TimeCalculate.convertDate(date);

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(dailyQuestionService.getDailyQuestions(userInfo.getId(), requestDate, reqTimeZone));
    }
}
