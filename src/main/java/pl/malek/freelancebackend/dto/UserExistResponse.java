package pl.malek.freelancebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserExistResponse {
    private boolean status;
}
