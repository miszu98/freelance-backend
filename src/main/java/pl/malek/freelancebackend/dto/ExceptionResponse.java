package pl.malek.freelancebackend.dto;

import lombok.Builder;
import lombok.Data;
import pl.malek.freelancebackend.enums.ErrorCode;

import java.util.List;

@Data
@Builder
public class ExceptionResponse {
    private ErrorCode errorCode;
    private List<String> messages;
    private String errorTime;
}
