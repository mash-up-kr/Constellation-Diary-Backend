package com.kancho.horoscope.controller;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.RequestWrongFieldException;
import com.kancho.common.util.TimeCalculate;
import com.kancho.horoscope.application.HoroscopeService;
import com.kancho.horoscope.dto.ResHoroscopeDto;
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

    @ApiOperation(value = "선택한 별자리 운세 보기 - 요청시 Time-Zone 선택, Response Date 단순 날짜(변환x)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "선택한 별자리 운세 보기 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4007 - Not Found Horoscope"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca"),
            @ApiImplicitParam(name = "constellation", value = "Constellation Name",
                    required = true, dataType = "string", paramType = "query",
                    defaultValue = "물병자리"),
            @ApiImplicitParam(name = "date", value = "Request Date",
                    required = true, dataType = "string", paramType = "query",
                    defaultValue = "yyyy-MM-ddTHH:mm:ss.SSSZ")
    })
    @GetMapping("/horoscopes")
    public ResponseEntity<ResHoroscopeDto> findHoroscopeByConstellation(
            @RequestParam("constellation") String constellation,
            @RequestParam("date") String date,
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        Date requestDate = TimeCalculate.convertDate(date);

        if (constellation == null) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(horoscopeService.findHoroscope(constellation, requestDate, reqTimeZone));
    }

    @ApiOperation(value = "운세 아이디로 운세 보기 - 요청시 Time-Zone 선택, Response Date 단순 날짜(변환x)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "운세 아이디로 운세 보기 성공"),
            @ApiResponse(code = 400, message = "4007 - Not Found Horoscope"),
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
            @PathVariable("horoscope-id") Long horoscopeId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(horoscopeService.findHoroscope(horoscopeId));
    }


}
