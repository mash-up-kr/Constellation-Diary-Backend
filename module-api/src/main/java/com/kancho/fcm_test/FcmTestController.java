package com.kancho.fcm_test;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FcmTestController {
    private final FcmTestService fcmTestService;

    @ApiOperation(value = "FCM Push")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Fcm Push 요청 성공"),
            @ApiResponse(code = 401, message = "4101 - Fail Authentication check token"),
            @ApiResponse(code = 500, message = "5001 - Fcm Send Error or 서버 에러")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization JWT",
                    required = true, dataType = "string", paramType = "header",
                    defaultValue = "Bearer cbbb1a6e-8614-4a4d-a967-b0a42924e7ca")
    })
    @PostMapping("/push")
    public ResponseEntity<Void> sendPush(@RequestHeader("Authorization") String token) {

        fcmTestService.send(token);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
