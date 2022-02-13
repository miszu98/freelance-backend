package pl.malek.freelancebackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.malek.freelancebackend.entity.ProcessEntity;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.repository.ProcessRegisterUserRepository;
import pl.malek.freelancebackend.service.ProcessRegisterUserService;

import java.util.UUID;

@Slf4j
@Service
public class ProcessRegisterUserServiceImpl implements ProcessRegisterUserService {

    @Autowired
    private ProcessRegisterUserRepository processRegisterUserRepository;

    @Override
    public ProcessEntity saveProcess(UserEntity userEntity) {
        log.info("Saving UUID register user process...");
        return processRegisterUserRepository.save(
                ProcessEntity.builder()
                        .processUuid(UUID.randomUUID())
                        .userEntity(userEntity)
                        .build()
        );
    }

}
