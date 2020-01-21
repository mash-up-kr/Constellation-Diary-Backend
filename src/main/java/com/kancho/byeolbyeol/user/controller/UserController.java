package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.user.application.UserService;
import com.kancho.byeolbyeol.user.dto.requset.ReqModifyQuestionAlarmDto;
import com.kancho.byeolbyeol.user.dto.requset.*;
import com.kancho.byeolbyeol.user.dto.response.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Api(description = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "아이디 중복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "아이디 중복 검사 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/users/check")
    public ResponseEntity<ResCheckUserDto> checkUserId(
            @RequestParam("user-id") String checkUserId) {

        if (checkUserId == null) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.duplicateCheck(checkUserId));
    }

    @ApiOperation(value = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 가입 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4007 - Exists UserId"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PostMapping("/users/sign-up")
    public ResponseEntity<ResUserInfoDto> signUp(
            @RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(reqSignUpDto));
    }

    @ApiOperation(value = "로그인 - questionTime(UTC)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4006 - Not Found User"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/users/sign-in")
    public ResponseEntity<ResUserInfoDto> signIn(
            @RequestBody @Valid ReqSignInDto reqSignInDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(reqSignInDto));
    }

    @ApiOperation(value = "유저 별자리 변경 - questionTime(UTC)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 별자리 변경 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4006 - Not Found User"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Refresh JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PatchMapping("/users/constellations")
    public ResponseEntity<ResUserDto> modifyConstellation(
            @RequestBody @Valid ReqModifyConstellationDto reqModifyConstellationDto) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.modifyConstellations(userInfo, reqModifyConstellationDto));
    }

    @ApiOperation(value = "질문 푸시알람 설정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "질문 푸시알람 설정 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4006 - Not Found User"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Refresh JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PatchMapping("/users/question-alarm")
    public ResponseEntity<ResUserDto> modifyQuestionAlarm(
            @RequestBody @Valid ReqModifyQuestionAlarmDto reqModifyQuestionAlarmDto) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.modifyQuestionAlarm(userInfo, reqModifyQuestionAlarmDto));
    }

    @ApiOperation(value = "운세 푸시알람 설정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "운세 푸시알람 설정 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4004 - Not Found Constellation, 4006 - Not Found User"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Refresh JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PatchMapping("/users/horoscope-alarm")
    public ResponseEntity<ResUserDto> modifyHoroscopeAlarm(
            @RequestBody @Valid ReqModifyHoroscopeAlarmDto reqModifyHoroscopeAlarmDto) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.modifyHoroscopeAlarm(userInfo, reqModifyHoroscopeAlarmDto));
    }

    @ApiOperation(value = "토큰 재발급")
    @ApiResponses({
            @ApiResponse(code = 200, message = "토큰 재발급 성공"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Refresh JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/users/tokens")
    public ResponseEntity<ResTokenDto> refreshToken() {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(userService.refreshToken(userInfo));
    }

}
