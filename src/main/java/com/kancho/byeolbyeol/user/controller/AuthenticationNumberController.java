package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.AuthenticationNumberService;
import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import com.kancho.byeolbyeol.user.dto.ResRegisterTokenDto;
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
            @RequestBody @Valid ReqEmailDto reqEmailDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        authenticationNumberService.generateAuthenticationNumber(reqEmailDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/authentication")
    public ResponseEntity<ResRegisterTokenDto> validationAuthenticationNumber(
            @RequestParam("authentication-number") Long number,
            @RequestParam("email") String email) {

        if (number == null || email == null) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationNumberService.validation(email, number));
    }
}