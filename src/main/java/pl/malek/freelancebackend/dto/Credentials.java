package pl.malek.freelancebackend.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class Credentials {
    private String email;
    private String password;
}
