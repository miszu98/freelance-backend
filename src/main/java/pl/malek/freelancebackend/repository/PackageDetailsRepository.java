package pl.malek.freelancebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.freelancebackend.entity.PackageDetailsEntity;

@Repository
public interface PackageDetailsRepository extends JpaRepository<PackageDetailsEntity, Long> {
}
