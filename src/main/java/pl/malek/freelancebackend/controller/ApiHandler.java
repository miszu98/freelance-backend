package pl.malek.freelancebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.freelancebackend.dto.ExceptionResponse;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.exception.enums.ErrorCodes;
import pl.malek.freelancebackend.utils.Utils;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(UserAccountValidationException.class)
    public ResponseEntity<ExceptionResponse> handleUserAccountValidationException(UserAccountValidationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .errorCode(ErrorCodes.USER_VALIDATION_ERROR)
                        .errorTime(Utils.formatDate(LocalDateTime.now()))
                        .messages(e.getMessages())
                        .build()
        );
    }

}
