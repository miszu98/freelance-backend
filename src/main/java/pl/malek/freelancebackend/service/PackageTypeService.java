package pl.malek.freelancebackend.service;

import pl.malek.freelancebackend.entity.OfferEntity;
import pl.malek.freelancebackend.entity.PackageTypeEntity;

import java.util.List;

public interface PackageTypeService {

    List<PackageTypeEntity> createPackageTypes(List<PackageTypeEntity> packageTypeEntities,
                                               OfferEntity offerEntity);

}
