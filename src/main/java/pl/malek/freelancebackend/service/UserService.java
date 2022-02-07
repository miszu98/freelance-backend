package pl.malek.freelancebackend.service;

import org.springframework.validation.BindingResult;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;

public interface UserService {

    User register(User user, BindingResult result) throws UserAccountValidationException, UserAlreadyExistException;
    boolean checkIfUserExist(String email);
}
