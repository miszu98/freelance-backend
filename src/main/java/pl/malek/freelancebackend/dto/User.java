package pl.malek.freelancebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull(message = "Field 'email' cannot be blank")
    @Email(message = "Field 'email' is wrong format")
    private String email;

    private String password;

    @Size(min = 2, message = "Field 'firstName' length should be greater than 2")
    private String firstName;

    @Size(min = 2, message = "Field 'lastName' length should be greater than 2")
    private String lastName;

    @Size(min = 9, max = 9, message = "Field 'phoneNumber' length should be equals 9")
    private String phoneNumber;

}
