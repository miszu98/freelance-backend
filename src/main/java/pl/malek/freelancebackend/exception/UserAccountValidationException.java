package pl.malek.freelancebackend.exception;


import lombok.Getter;

import java.util.List;

@Getter
public class UserAccountValidationException extends Exception {

    private List<String> messages;

    public UserAccountValidationException(List<String> messages) {
        this.messages = messages;
    }
}
