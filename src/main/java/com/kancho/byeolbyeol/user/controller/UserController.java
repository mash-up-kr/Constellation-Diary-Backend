package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.UserService;
import com.kancho.byeolbyeol.user.dto.requset.*;
import com.kancho.byeolbyeol.user.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/check")
    public ResponseEntity<ResCheckUserDto> checkUserId(
            @RequestParam("user-id") String checkUserId) {

        if (checkUserId == null) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.duplicateCheck(checkUserId));
    }

    @PostMapping("/users/sign-up")
    public ResponseEntity<ResUserInfoDto> signUp(
            @RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(reqSignUpDto));
    }

    @PostMapping("/users/sign-in")
    public ResponseEntity<ResUserInfoDto> signIn(
            @RequestBody @Valid ReqSignInDto reqSignInDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(reqSignInDto));
    }

    @GetMapping("/users/tokens")
    public ResponseEntity<ResTokenDto> refreshToken(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.refreshToken(token));
    }


    @PostMapping("/users/find-id")
    public ResponseEntity<ResUserIdDto> findUserId(
            @RequestBody @Valid ReqValidationNumberDto reqValidationNumberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserId(reqValidationNumberDto));
    }
}
