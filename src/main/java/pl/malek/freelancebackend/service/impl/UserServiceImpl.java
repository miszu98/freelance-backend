package pl.malek.freelancebackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;
import pl.malek.freelancebackend.exception.enums.Role;
import pl.malek.freelancebackend.repository.UserRepository;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(User user, BindingResult result)
            throws UserAccountValidationException, UserAlreadyExistException {
        if (result.hasErrors()) {
            log.error(getErrorMessages(result.getAllErrors()).toString());
            throw new UserAccountValidationException(getErrorMessages(result.getAllErrors()));
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error(String.format("User already exist with email: %s", user.getEmail()));
            throw new UserAlreadyExistException(String.format("User with email: %s already exist", user.getEmail()));
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        log.info("Saving user to database...");
        return modelMapper.map(userEntity, User.class);
    }


    private List<String> getErrorMessages(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
