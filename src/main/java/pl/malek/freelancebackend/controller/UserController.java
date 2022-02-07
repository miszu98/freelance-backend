package pl.malek.freelancebackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.dto.UserExistResponse;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;
import pl.malek.freelancebackend.service.UserService;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result)
            throws UserAccountValidationException, UserAlreadyExistException {
        log.info("Register user running... " + user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(user, result));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserExistResponse> checkIfUserExist(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(
                UserExistResponse.builder()
                        .status(userService.checkIfUserExist(email))
                        .build()
        );
    }


}
