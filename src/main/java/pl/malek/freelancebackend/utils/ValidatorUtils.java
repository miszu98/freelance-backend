package pl.malek.freelancebackend.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidatorUtils {

    public boolean validate(BindingResult result) {
        return result.hasErrors();
    }

    public Set<String> extractErrorMessages(BindingResult result) {
        return result.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toSet());
    }

}
