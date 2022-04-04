package pl.malek.freelancebackend.mapper;

import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.entity.OfferEntity;

public interface OfferMapper {

    OfferEntity mapToOfferEntity(Offer offer);

}
