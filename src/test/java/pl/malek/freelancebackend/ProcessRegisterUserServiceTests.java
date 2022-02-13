package pl.malek.freelancebackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.malek.freelancebackend.entity.ProcessEntity;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.exception.enums.Role;
import pl.malek.freelancebackend.repository.ProcessRegisterUserRepository;
import pl.malek.freelancebackend.service.impl.ProcessRegisterUserServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class ProcessRegisterUserServiceTests {

    @Mock
    private ProcessRegisterUserRepository processRegisterUserRepository;

    @InjectMocks
    private ProcessRegisterUserServiceImpl processRegisterUserService;

    @Test
    void shouldGenerateUuidAndMappedWithUser() {
        UserEntity userEntity = UserEntity.builder()
                .id(324L)
                .email("adam.nowak123@wp.pl")
                .firstName("Adam")
                .lastName("Nowak")
                .password("$2a$12$Hu9xmTwxPWITNfOgirirpexA5kkZtmHk/yD.14QOc006BzllZVyv2")
                .role(Role.USER).build();

        ProcessEntity process = ProcessEntity.builder()
                .processUuid(UUID.randomUUID())
                .userEntity(userEntity)
                .build();

        processRegisterUserService.saveProcess(userEntity);

        ArgumentCaptor<ProcessEntity> captor =
                ArgumentCaptor.forClass(ProcessEntity.class);

        verify(processRegisterUserRepository).save(captor.capture());

        ProcessEntity captured = captor.getValue();

        assertEquals(captured.getUserEntity().getEmail(),
                process.getUserEntity().getEmail());
    }
}
