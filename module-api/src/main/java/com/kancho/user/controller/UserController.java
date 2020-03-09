package com.kancho.user.controller;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.RequestWrongFieldException;
import com.kancho.common.user_context.ThreadContext;
import com.kancho.common.user_context.UserInfo;
import com.kancho.user.application.UserService;
import com.kancho.user.dto.requset.*;
import com.kancho.user.dto.response.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@Api(description = "유저 - 로그인 & 회원가입 API")
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
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.duplicateCheck(checkUserId));
    }

    @ApiOperation(value = "아이디 찾기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "아이디 찾기 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, 4003 - Nonexistent User By Email"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/users/find-id")
    public ResponseEntity<ResUserIdDto> findUserId(
            @RequestParam("email") @Email String email) {

        if (email == null) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserId(email));
    }

    @ApiOperation(value = "회원 가입 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 가입 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4005 - Not Found Constellation, 4006 - Exists UserId"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Sign UP JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PostMapping("/users/sign-up")
    public ResponseEntity<ResUserInfoDto> signUp(
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
            @RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(reqTimeZone, reqSignUpDto));
    }

    @ApiOperation(value = "로그인 - 요청시 Time-Zone 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4104 - Nonexistent UserId, 4105 - Is Not Equal To Password"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/users/sign-in")
    public ResponseEntity<ResUserInfoDto> signIn(
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone,
            @RequestBody @Valid ReqSignInDto reqSignInDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(reqTimeZone, reqSignInDto));
    }

    @ApiOperation(value = "로그아웃")
    @ApiResponses({
            @ApiResponse(code = 204, message = "로그아웃 성공"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/users/sign-out")
    public ResponseEntity<Void> signOut() {

        UserInfo userInfo = ThreadContext.userInfo.get();

        userService.signOut(userInfo);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "유저 정보 조회 - 인증 토큰 이용(시간 정보는 UTC로 내려갑니다. TimeZone을 이용하여 변환 시켜 주세요)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 조회 성공"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authentication JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @GetMapping("/users")
    public ResponseEntity<ResUserDto> getUser(
            @RequestHeader(value = "Time-Zone") ReqTimeZone reqTimeZone) {

        UserInfo userInfo = ThreadContext.userInfo.get();

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(reqTimeZone, userInfo));
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
