package pl.malek.freelancebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.freelancebackend.entity.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
