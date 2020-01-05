package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.UserSignUpService;
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
public class UserSignUpController {

    private final UserSignUpService userSignUpService;

    @PostMapping("/sign-up/authentication-numbers")
    public ResponseEntity<Void> generateAuthenticationNumber(
            @RequestBody @Valid ReqEmailDto reqEmailDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        userSignUpService.generateAuthenticationNumber(reqEmailDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/sign-up/authentication")
    public ResponseEntity<ResRegisterTokenDto> validationAuthenticationNumber(
            @RequestParam("authentication-number") Long number,
            @RequestParam("email") String email) {

        if (number == null || email == null) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(userSignUpService.validation(email, number));
    }
}
