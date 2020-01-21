package com.kancho.byeolbyeol.horoscope.controller;

import com.kancho.byeolbyeol.common.constant.ReqTimeZone;
import com.kancho.byeolbyeol.common.exception.RequestWrongFieldException;
import com.kancho.byeolbyeol.horoscope.dto.ResHoroscopeDto;
import com.kancho.byeolbyeol.horoscope.application.HoroscopeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@Api(description = "운세 관련 API")
public class HoroscopeController {

    private final HoroscopeService horoscopeService;

    @ApiOperation(value = "선택한 별자리 오늘의 운세 보기 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "선택한 별자리 오늘의 운세 보기 성공"),
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
    public ResponseEntity<ResHoroscopeDto> findHoroscopeByConstellation(
            @RequestParam("constellation") String constellationName,
            @RequestParam("date") Date date,
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        if (constellationName == null) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(horoscopeService.findHoroscope(constellationName, date, reqTimeZone));
    }

    @ApiOperation(value = "운세 아이디로 운세 보기 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "운세 아이디로 운세 보기 성공"),
            @ApiResponse(code = 400, message = "4004 - Not Found Constellation, 4009 - Not Found Horoscope"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/horoscopes/{horoscope-id:^[0-9]+$}")
    public ResponseEntity<ResHoroscopeDto> findHoroscopeByHoroscopeId(
            @PathVariable("horoscope-id") Long horoscopeId,
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(horoscopeService.findHoroscope(horoscopeId, reqTimeZone));
    }


}
