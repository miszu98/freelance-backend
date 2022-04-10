package pl.malek.freelancebackend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.service.OfferService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/offer")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody @Valid Offer offer, BindingResult result) {
        log.info("Trying to create new offer with title: " + offer.getTitle());
        offerService.create(offer, result);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
