package pl.malek.freelancebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.malek.freelancebackend.Validators.ValidPassword;
import pl.malek.freelancebackend.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "Field 'email' may be not blank")
    @Email(message = "Field 'email' is wrong format")
    private String email;

    @ValidPassword
    private String password;

    @Size(min = 2, message = "Field 'firstName' length should be greater than 2")
    private String firstName;

    @Size(min = 2, message = "Field 'lastName' length should be greater than 2")
    private String lastName;

    private Role role;
}
