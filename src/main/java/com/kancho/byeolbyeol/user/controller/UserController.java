package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.UserService;
import com.kancho.byeolbyeol.user.dto.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.ResCheckUserDto;
import com.kancho.byeolbyeol.user.dto.ResSignUpDto;
import com.kancho.byeolbyeol.user.dto.ResUserDto;
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
    public ResponseEntity<ResSignUpDto> signUp(
            @RequestBody @Valid ReqSignUpDto reqSignUpDto, BindingResult bindingResult,
            @RequestHeader("Authorization") String token) {

        if (bindingResult.hasErrors()) {
            throw new RequestWornFieldException();
        }

        jwtManager.authenticate(token);

        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(reqSignUpDto));
    }
}
