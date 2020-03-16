package com.kancho.exception;

import com.kancho.constellation.NotFoundConstellationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResExceptionDto> restExceptionHandler(HttpServletRequest req, BaseException exception)
            throws RuntimeException {
        ErrorModel errorModel = exception.getErrorModel();
        log.error("Method:{} - RequestUrl:{} - Status:{} - Msg:{}",
                req.getMethod(),
                req.getRequestURI(),
                errorModel.getHttpStatus(),
                errorModel.getMessage());

        return ResponseEntity
                .status(errorModel.getHttpStatus())
                .body(ResExceptionDto.builder()
                        .errorModel(errorModel)
                        .build());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Exception> unhandledExceptionHandler(RuntimeException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }

    @ExceptionHandler(value = NotFoundConstellationException.class)
    public ResponseEntity<ResExceptionDto> notFoundConstellationExceptionHandler(NotFoundConstellationException exception) {
        ErrorModel errorModel =
                ErrorModel.builder()
                        .code(4005)
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(exception.getMessage())
                        .build();

        return ResponseEntity
                .status(errorModel.getHttpStatus())
                .body(ResExceptionDto.builder()
                        .errorModel(errorModel)
                        .build());
    }
}
