package pl.malek.freelancebackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.freelancebackend.dto.Credentials;
import pl.malek.freelancebackend.dto.JwtResponse;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;
import pl.malek.freelancebackend.exception.enums.Role;
import pl.malek.freelancebackend.repository.UserRepository;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.service.ProcessRegisterUserService;
import pl.malek.freelancebackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final ProcessRegisterUserService processRegisterUserService;

    @Override
    @Transactional
    public User register(User user, BindingResult result)
            throws UserAccountValidationException, UserAlreadyExistException {
        if (result.hasErrors()) {
            log.error(getErrorMessages(result.getAllErrors()).toString());
            throw new UserAccountValidationException(getErrorMessages(result.getAllErrors()));
        }

        if (checkIfUserExist(user.getEmail())) {
            log.error(String.format("User already exist with email: %s", user.getEmail()));
            throw new UserAlreadyExistException(String.format("User with email: %s already exist", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity userEntity = userRepository.save(objectMapper.convertValue(user, UserEntity.class));
        log.info("Saving user to database...");

        processRegisterUserService.saveProcess(userEntity);

        return objectMapper.convertValue(userEntity, User.class);
    }

    @Override
    public JwtResponse authenticate(Credentials credentials) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(), credentials.getPassword()
            ));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return createToken(userDetailsService.loadUserByUsername(credentials.getEmail()));
    }

    private JwtResponse createToken(UserDetails userDetails) {
        return JwtResponse.builder().token(jwtTokenUtil.generateToken(userDetails)).build();
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private List<String> getErrorMessages(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
