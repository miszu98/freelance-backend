package pl.malek.freelancebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.malek.freelancebackend.entity.OfferEntity;
import pl.malek.freelancebackend.entity.PackageTypeEntity;
import pl.malek.freelancebackend.repository.PackageTypeRepository;
import pl.malek.freelancebackend.service.PackageTypeService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageTypeServiceImpl implements PackageTypeService {

    private final PackageTypeRepository packageTypeRepository;

    @Override
    public List<PackageTypeEntity> createPackageTypes(List<PackageTypeEntity> packageTypeEntities,
                                                      OfferEntity offerEntity) {
        packageTypeEntities.forEach(e -> e.setOfferEntity(offerEntity));
        return packageTypeRepository.saveAll(packageTypeEntities);
    }

}
