package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
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

    @ApiOperation(value = "인증번호 생성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "인증번호 생성 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication-numbers")
    public ResponseEntity<Void> generateAuthenticationNumber(
            @RequestBody @Valid ReqAuthenticationNumbersDto reqAuthenticationNumbersDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        authenticationNumberService.sendAuthenticationNumber(reqAuthenticationNumbersDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "인증번호 인증 - SIGN_UP(회원가입)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증번호 인증 - SIGN_UP(회원가입) 성공"),
            @ApiResponse(code = 400, message = "4001 - Request Worn Field, " +
                    "4002 - Not Found Authentication-Number, 4003 - Is Not Same Authentication-Number"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/authentication")
    public ResponseEntity<ResRegisterTokenDto> verifyAuthenticationNumber(
            @RequestBody @Valid ReqValidationNumberDto reqValidationNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationNumberService.verify(reqValidationNumberDto));
    }

}
