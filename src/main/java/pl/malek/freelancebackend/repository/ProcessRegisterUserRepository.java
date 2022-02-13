package pl.malek.freelancebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.freelancebackend.entity.ProcessEntity;

@Repository
public interface ProcessRegisterUserRepository extends JpaRepository<ProcessEntity, Long> {
}
