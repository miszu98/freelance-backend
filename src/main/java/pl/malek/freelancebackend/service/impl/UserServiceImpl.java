package pl.malek.freelancebackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.malek.freelancebackend.dto.ExceptionResponse;
import pl.malek.freelancebackend.exception.enums.ErrorCodes;
import pl.malek.freelancebackend.repository.UserRepository;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.service.UserService;
import pl.malek.freelancebackend.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User register(User user, BindingResult result)
            throws UserAccountValidationException {
        if (result.hasErrors()) {
            throw new UserAccountValidationException(getErrorMessages(result.getAllErrors()));
        }
        return null;
    }




    private List<String> getErrorMessages(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }


}
