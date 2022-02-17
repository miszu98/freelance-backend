package pl.malek.freelancebackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.freelancebackend.dto.ExceptionResponse;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;
import pl.malek.freelancebackend.enums.ErrorCode;
import pl.malek.freelancebackend.utils.Utils;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(UserAccountValidationException.class)
    public ResponseEntity<ExceptionResponse> handleUserAccountValidationException(UserAccountValidationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .errorCode(ErrorCode.USER_VALIDATION_ERROR)
                        .errorTime(Utils.formatDate(LocalDateTime.now()))
                        .messages(e.getMessages())
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .errorCode(ErrorCode.JSON_PARSE_ERROR)
                        .errorTime(Utils.formatDate(LocalDateTime.now()))
                        .messages(Collections.singletonList(e.getMessage()))
                        .build()
        );
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .errorCode(ErrorCode.USER_ALREADY_EXIST)
                        .errorTime(Utils.formatDate(LocalDateTime.now()))
                        .messages(Collections.singletonList(e.getMessage()))
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .errorCode(ErrorCode.UNKNOWN_ERROR)
                        .errorTime(Utils.formatDate(LocalDateTime.now()))
                        .messages(Collections.singletonList(e.getMessage()))
                        .build()
        );
    }

}
