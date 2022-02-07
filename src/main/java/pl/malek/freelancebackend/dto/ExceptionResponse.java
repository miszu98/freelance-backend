package pl.malek.freelancebackend.dto;

import lombok.Builder;
import lombok.Data;
import pl.malek.freelancebackend.exception.enums.ErrorCodes;

import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    private ErrorCodes errorCode;
    private List<String> messages;
    private String errorTime;
}
