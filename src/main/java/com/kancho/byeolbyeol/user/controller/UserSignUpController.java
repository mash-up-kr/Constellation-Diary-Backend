package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.user.application.UserSignUpService;
import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserSignUpController {

    private final UserSignUpService userSignUpService;

    @PostMapping("/sign-up/authentication-number")
    public ResponseEntity<Void> generateAuthenticationNumber(
            @RequestBody @Valid ReqEmailDto reqEmailDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }

        userSignUpService.generateAuthenticationNumber(reqEmailDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
