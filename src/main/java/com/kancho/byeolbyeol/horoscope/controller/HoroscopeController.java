package com.kancho.byeolbyeol.horoscope.controller;

import com.kancho.byeolbyeol.common.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.horoscope.dto.ResHoroscopeDto;
import com.kancho.byeolbyeol.horoscope.application.HoroscopeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HoroscopeController {

    private final HoroscopeService horoscopeService;

    @ApiOperation(value = "선택한 별자리 오늘의 운세 보기 - date(TIME ZONE X, 단순 날짜)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "선택한 별자리 오늘의 운세 보기"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4009 - Not Found Horoscope"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/horoscopes")
    public ResponseEntity<ResHoroscopeDto> deleteDiary(
            @RequestParam("constellation") String constellationName) {

        if(constellationName == null) {
            throw new RequestWornFieldException();
        }

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(horoscopeService.findDailyByConstellation(userInfo, constellationName));
    }
}
