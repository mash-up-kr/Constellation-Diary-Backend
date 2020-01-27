package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.exception.RequestWrongFieldException;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.requset.ReqFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResAuthenticationTokenDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(description = "인증 번호 관련 API")
public class AuthenticationNumberController {

    private final AuthenticationNumberService authenticationNumberService;

    @ApiOperation(value = "인증번호 생성 - SIGN_UP(회원가입)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "인증번호 생성 - SIGN_UP(회원가입) 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication-numbers/sign-up")
    public ResponseEntity<Void> generateSignUpNumber(
            @RequestBody @Valid ReqSignUpNumberDto reqSignUpNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        authenticationNumberService.sendSignUpNumber(reqSignUpNumberDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "인증번호 인증 - SIGN_UP(회원가입)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증번호 인증 - SIGN_UP(회원가입) 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4102 - Fail Authentication-Number(check field)"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication/sign-up")
    public ResponseEntity<ResAuthenticationTokenDto> verifySignUpNumber(
            @RequestBody @Valid ReqValidationSignUpNumberDto reqValidationSignUpNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationNumberService.verifySignUpNumber(reqValidationSignUpNumberDto));
    }

    @ApiOperation(value = "인증번호 생성 - FIND_PASSWORD(비밀번호 찾기)")
    @ApiResponses({
            @ApiResponse(code = 201, message = "인증번호 생성 - FIND_PASSWORD(비밀번호 찾기) 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, 4002 - Is Not Exists User"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication-numbers/find-password")
    public ResponseEntity<Void> generateFindPasswordNumber(
            @RequestBody @Valid ReqFindPasswordNumberDto reqFindPasswordNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        authenticationNumberService.sendFindPasswordNumber(reqFindPasswordNumberDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "인증번호 인증 - FIND_PASSWORD(비밀번호 찾기)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증번호 인증 - FIND_PASSWORD(비밀번호 찾기) 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 401, message = "4102 - Fail Authentication-Number(check field)"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication/find-password")
    public ResponseEntity<ResAuthenticationTokenDto> verifyFindPasswordNumber(
            @RequestBody @Valid ReqValidationFindPasswordNumberDto reqValidationFindPasswordNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWrongFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationNumberService.verifyFindPasswordNumber(reqValidationFindPasswordNumberDto));
    }

}
