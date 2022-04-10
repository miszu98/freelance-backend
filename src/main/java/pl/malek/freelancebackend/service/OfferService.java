package pl.malek.freelancebackend.service;

import org.springframework.validation.BindingResult;
import pl.malek.freelancebackend.dto.Offer;

public interface OfferService {

    void create(Offer offer, BindingResult result);

}
