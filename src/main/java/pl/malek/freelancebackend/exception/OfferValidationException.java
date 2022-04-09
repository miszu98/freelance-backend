package pl.malek.freelancebackend.exception;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class OfferValidationException extends RuntimeException {
    private Set<String> messages;
    public OfferValidationException(Set<String> messages) {
        this.messages = messages;
    }
}
