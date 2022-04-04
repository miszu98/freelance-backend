package pl.malek.freelancebackend.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.entity.OfferEntity;
import pl.malek.freelancebackend.mapper.OfferMapper;


@Slf4j
@Component
@RequiredArgsConstructor
public class OfferMapperImpl implements OfferMapper {

    private final ObjectMapper objectMapper;

    @Override
    public OfferEntity mapToOfferEntity(Offer offer) {
        log.info("Mapping offer dto to entity");
        return objectMapper.convertValue(offer, OfferEntity.class);
    }

}
