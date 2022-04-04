package pl.malek.freelancebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.malek.freelancebackend.entity.PackageDetailsEntity;
import pl.malek.freelancebackend.entity.PackageTypeEntity;
import pl.malek.freelancebackend.repository.PackageDetailsRepository;
import pl.malek.freelancebackend.service.PackageDetailsService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageDetailsServiceImpl implements PackageDetailsService {

    private final PackageDetailsRepository packageDetailsRepository;

    @Override
    public void createPackageDetails(List<PackageTypeEntity> packageTypeEntities) {
        packageTypeEntities.forEach(e -> {
            List<PackageDetailsEntity> detailsEntities = e.getPackageDetailsEntities();
            detailsEntities.forEach(d -> d.setPackageTypeEntity(e));
            packageDetailsRepository.saveAll(detailsEntities);
        });
    }

}
