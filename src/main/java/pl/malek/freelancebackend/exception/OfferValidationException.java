package pl.malek.freelancebackend.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class OfferValidationException extends RuntimeException {
    private List<String> messages;
    public OfferValidationException(List<String> messages) {
        this.messages = messages;
    }
}
