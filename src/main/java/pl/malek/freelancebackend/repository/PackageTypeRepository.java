package pl.malek.freelancebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.freelancebackend.entity.PackageTypeEntity;

@Repository
public interface PackageTypeRepository extends JpaRepository<PackageTypeEntity, Long> {
}
