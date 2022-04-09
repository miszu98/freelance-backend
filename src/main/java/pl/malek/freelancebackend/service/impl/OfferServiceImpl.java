package pl.malek.freelancebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.malek.freelancebackend.dto.OfferDetails;
import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.entity.OfferEntity;
import pl.malek.freelancebackend.entity.PackageTypeEntity;
import pl.malek.freelancebackend.exception.OfferValidationException;
import pl.malek.freelancebackend.mapper.OfferMapper;
import pl.malek.freelancebackend.repository.OfferRepository;
import pl.malek.freelancebackend.service.OfferService;
import pl.malek.freelancebackend.service.PackageDetailsService;
import pl.malek.freelancebackend.service.PackageTypeService;
import pl.malek.freelancebackend.utils.ValidatorUtils;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final ValidatorUtils validatorUtils;

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    private final PackageTypeService packageTypeService;

    private final PackageDetailsService packageDetailsService;

    @Override
    public OfferDetails create(Offer offer, BindingResult result) {
        boolean isError = validatorUtils.validate(result);

        if (isError) {
            log.info("Founded errors while validating");
            log.info("Throwing exception");
            Set<String> messages = validatorUtils.extractErrorMessages(result);
            throw new OfferValidationException(messages);
        } else {
            log.info("Not founded errors while validating");
            OfferEntity offerEntity = offerMapper.mapToOfferEntity(offer);
            log.info("Saving offer");
            offerEntity = offerRepository.save(offerEntity);

            List<PackageTypeEntity> packageTypeEntities =
                    packageTypeService.createPackageTypes(offerEntity.getPackageTypeEntities(), offerEntity);

            packageDetailsService.createPackageDetails(packageTypeEntities);
        }

        return null;
    }


}
