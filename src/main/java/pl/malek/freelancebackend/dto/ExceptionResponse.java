package pl.malek.freelancebackend.dto;

import lombok.Builder;
import pl.malek.freelancebackend.exception.enums.ErrorCodes;

import java.util.List;

@Builder
public class ExceptionResponse {
    private ErrorCodes errorCode;
    private List<String> messages;
    private String errorTime;
}
