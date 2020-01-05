package com.kancho.byeolbyeol.user.controller;

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
        //TODO Exception 처리
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }

        userSignUpService.generateAuthenticationNumber(reqEmailDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/sign-up/authentication")
    public ResponseEntity<ResRegisterTokenDto> validationAuthenticationNumber(
            @RequestParam("authentication-number") Long number,
            @RequestParam("email") String email) {
        //TODO Exception 처리
        if (number == null) {
            throw new RuntimeException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(userSignUpService.validation(email, number));
    }
}
