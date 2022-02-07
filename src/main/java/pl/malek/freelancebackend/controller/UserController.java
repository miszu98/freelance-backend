package pl.malek.freelancebackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.malek.freelancebackend.dto.User;
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


}
