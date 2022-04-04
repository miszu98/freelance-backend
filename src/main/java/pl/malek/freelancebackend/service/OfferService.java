package pl.malek.freelancebackend.service;

import org.springframework.validation.BindingResult;
import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.dto.OfferDetails;

public interface OfferService {

    OfferDetails create(Offer offer, BindingResult result);

}
