package pl.malek.freelancebackend.service;

import pl.malek.freelancebackend.entity.PackageTypeEntity;

import java.util.List;

public interface PackageDetailsService {

    void createPackageDetails(List<PackageTypeEntity> packageTypeEntities);

}
