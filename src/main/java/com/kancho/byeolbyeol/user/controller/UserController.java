package com.kancho.byeolbyeol.user.controller;

import com.kancho.byeolbyeol.exception.RequestWornFieldException;
import com.kancho.byeolbyeol.user.application.UserService;
import com.kancho.byeolbyeol.user.dto.ResCheckUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ResCheckUserDto> checkUserId(
            @RequestParam("check-id") String checkUserId) {

        if (checkUserId == null) {
            throw new RequestWornFieldException();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.duplicateCheck(checkUserId));
    }
}
