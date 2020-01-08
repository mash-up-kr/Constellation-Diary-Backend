package com.kancho.byeolbyeol.home.controller;

import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.home.application.HomeService;
import com.kancho.byeolbyeol.home.dto.ResHomeViewDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @ApiOperation(value = "Home API - question 필드에 일기 제목 or 질문, existDiary - 일기 존재 여부")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Home API 성공"),
            @ApiResponse(code = 400, message = "4006 - Not Found User, 4004 - Not Found Constellation," +
                    " 4008 - Not Found Question, 4009 - Not Found Horoscope"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/home")
    public ResponseEntity<ResHomeViewDto> getHomeView() {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(homeService.getHomeView(userInfo.getId()));
    }
}
