package pl.malek.freelancebackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.malek.freelancebackend.dto.Offer;
import pl.malek.freelancebackend.dto.PackageDetail;
import pl.malek.freelancebackend.dto.PackageType;
import pl.malek.freelancebackend.entity.OfferEntity;
import pl.malek.freelancebackend.entity.PackageTypeEntity;
import pl.malek.freelancebackend.exception.OfferValidationException;
import pl.malek.freelancebackend.mapper.OfferMapper;
import pl.malek.freelancebackend.repository.OfferRepository;
import pl.malek.freelancebackend.service.PackageDetailsService;
import pl.malek.freelancebackend.service.PackageTypeService;
import pl.malek.freelancebackend.service.impl.OfferServiceImpl;
import pl.malek.freelancebackend.utils.ValidatorUtils;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTests {

    @Mock
    private ValidatorUtils validatorUtils;

    @Mock
    private OfferMapper offerMapper;

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private PackageDetailsService packageDetailsService;

    @Mock
    private PackageTypeService packageTypeService;

    @InjectMocks
    private OfferServiceImpl underTest;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldValidateOfferWithErrorsResult() {
        Offer offer = new Offer();
        offer.setDescription("Test description");
        offer.setSubCategoryId(1);
        offer.setPackageTypes(List.of());

        var violationSet = validator.validate(offer);

        assertEquals(violationSet.size(), 3);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    void shouldValidateOfferWithNoErrors() {
        PackageDetail packageDetail = new PackageDetail();
        packageDetail.setDefinition("Test definition");
        packageDetail.setAvailability(true);

        PackageType packageType = new PackageType();
        packageType.setPackageType(pl.malek.freelancebackend.enums.PackageType.BASIC);
        packageType.setDeliveryTime(2);
        packageType.setPrice(new BigDecimal("10.00"));
        packageType.setShortDescription("Test short description, " +
                "Test short description, Test short description");
        packageType.setPackageDetails(List.of(packageDetail, packageDetail, packageDetail));

        Offer offer = new Offer();
        offer.setTitle("Fix your bugs");
        offer.setDescription("I have almost 5 years commercial experience as developer. " +
                "I can help you with bugs in your applications. I can create new app for you");
        offer.setSubCategoryId(1);
        offer.setPackageTypes(List.of(packageType, packageType, packageType));

        var violationSet = validator.validate(offer);

        assertEquals(violationSet.size(), 0);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    void shouldThrownExceptionWhileCreatingOffer() {
        Offer offer = new Offer();

        when(validatorUtils.validate(any())).thenReturn(true);
        when(validatorUtils.extractErrorMessages(any())).thenReturn(new HashSet<>());

        OfferValidationException exception = assertThrows(OfferValidationException.class,
                () -> underTest.create(offer, any()));


        assertEquals(exception.getMessages(), new HashSet<>());
    }

    @Test
    void shouldCreateNewOffer() {
        PackageTypeEntity packageTypeEntity = new PackageTypeEntity();
        Offer offer = new Offer();
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setTitle("Test title");
        offerEntity.setPackageTypeEntities(
                List.of(packageTypeEntity, packageTypeEntity, packageTypeEntity)
        );

        when(validatorUtils.validate(any())).thenReturn(false);
        when(offerMapper.mapToOfferEntity(any(Offer.class))).thenReturn(offerEntity);
        when(offerRepository.save(offerEntity)).thenReturn(offerEntity);

        ArgumentCaptor<OfferEntity> argumentCaptor = ArgumentCaptor.forClass(OfferEntity.class);

        underTest.create(offer, any());

        verify(offerRepository).save(argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().getTitle(), "Test title");
    }

    @Test
    void shouldCreatePackageTypesForOffer() {
        PackageTypeEntity packageTypeEntity = new PackageTypeEntity();
        Offer offer = new Offer();
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setTitle("Test title");
        offerEntity.setPackageTypeEntities(
                List.of(packageTypeEntity, packageTypeEntity, packageTypeEntity)
        );

        when(validatorUtils.validate(any())).thenReturn(false);
        when(offerMapper.mapToOfferEntity(offer)).thenReturn(offerEntity);
        when(offerRepository.save(offerEntity)).thenReturn(offerEntity);

        underTest.create(offer, any());

        verify(packageTypeService).createPackageTypes(offerEntity.getPackageTypeEntities(),
                offerEntity);

    }

    @Test
    void shouldCreateDetailsForPackages() {
        PackageTypeEntity packageTypeEntity = new PackageTypeEntity();
        Offer offer = new Offer();
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setTitle("Test title");
        offerEntity.setPackageTypeEntities(
                List.of(packageTypeEntity, packageTypeEntity, packageTypeEntity)
        );

        when(validatorUtils.validate(any())).thenReturn(false);
        when(offerMapper.mapToOfferEntity(offer)).thenReturn(offerEntity);
        when(offerRepository.save(offerEntity)).thenReturn(offerEntity);

        underTest.create(offer, any());

        verify(packageDetailsService).createPackageDetails(anyList());
    }
}
