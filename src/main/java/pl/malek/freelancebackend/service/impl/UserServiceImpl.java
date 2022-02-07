package pl.malek.freelancebackend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.repository.UserRepository;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public User register(User user, BindingResult result)
            throws UserAccountValidationException {
        if (result.hasErrors()) {
            log.info(getErrorMessages(result.getAllErrors()).toString());
            throw new UserAccountValidationException(getErrorMessages(result.getAllErrors()));
        }
        return modelMapper.map(userRepository.save(modelMapper.map(user, UserEntity.class)), User.class);
    }




    private List<String> getErrorMessages(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }


}
