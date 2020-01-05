package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.common.JWTManager;
import com.kancho.byeolbyeol.common.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.UserService;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignInDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.response.ResCheckUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
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
    private final JWTManager jwtManager;

    @GetMapping("/users")
    public ResponseEntity<ResCheckUserDto> checkUserId(
            @RequestParam("check-id") String checkUserId) {

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

    @GetMapping("/users/token")
    public ResponseEntity<ResTokenDto> refreshToken(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.refreshToken(token));
    }
}
