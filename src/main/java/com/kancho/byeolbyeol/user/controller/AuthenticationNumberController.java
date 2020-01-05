package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationNumberController {

    private final AuthenticationNumberService authenticationNumberService;

    @PostMapping("/authentication-numbers")
    public ResponseEntity<Void> generateAuthenticationNumber(
            @RequestBody @Valid ReqAuthenticationNumbersDto reqAuthenticationNumbersDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        authenticationNumberService.generateAuthenticationNumber(reqAuthenticationNumbersDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/authentication")
    public ResponseEntity<ResRegisterTokenDto> validationAuthenticationNumber(
            @RequestBody @Valid ReqValidationNumberDto reqValidationNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationNumberService.validation(reqValidationNumberDto));
    }
}
