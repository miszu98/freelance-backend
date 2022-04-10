package pl.malek.freelancebackend.exception;


import lombok.Getter;

import java.util.Set;

@Getter
public class UserAccountValidationException extends Exception {

    private Set<String> messages;

    public UserAccountValidationException(Set<String> messages) {
        this.messages = messages;
    }
}
