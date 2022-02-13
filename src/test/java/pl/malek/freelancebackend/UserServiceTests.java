package pl.malek.freelancebackend;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.entity.UserEntity;
import pl.malek.freelancebackend.exception.UserAccountValidationException;
import pl.malek.freelancebackend.exception.UserAlreadyExistException;
import pl.malek.freelancebackend.exception.enums.Role;
import pl.malek.freelancebackend.repository.UserRepository;
import pl.malek.freelancebackend.service.impl.ProcessRegisterUserServiceImpl;
import pl.malek.freelancebackend.service.impl.UserServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProcessRegisterUserServiceImpl processRegisterUserService;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Captor
    private ArgumentCaptor<UserEntity> captor;

    @Test
    void shouldAddUser() throws UserAccountValidationException, UserAlreadyExistException {
        User user = User.builder()
                .email("michal.malek98@wp.pl")
                .firstName("Adam")
                .lastName("Nowak")
                .password("abc123")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .email("michal.malek98@wp.pl")
                .firstName("Adam")
                .lastName("Nowak")
                .password("$2a$12$Hu9xmTwxPWITNfOgirirpexA5kkZtmHk/yD.14QOc006BzllZVyv2")
                .role(Role.USER).build();

        when(objectMapper.convertValue(user, UserEntity.class)).thenReturn(userEntity);
        when(passwordEncoder.encode("abc123"))
                .thenReturn("$2a$12$Hu9xmTwxPWITNfOgirirpexA5kkZtmHk/yD.14QOc006BzllZVyv2");

        userServiceImpl.register(user, bindingResult);

        verify(userRepository).save(captor.capture());

        assertEquals(user.getPassword(), userEntity.getPassword());
    }

    @Test
    void shouldReturnOneUser() {
        UserEntity userEntity = UserEntity.builder()
                .email("michal.malek98@wp.pl")
                .firstName("Adam")
                .lastName("Nowak")
                .password("$2a$12$Hu9xmTwxPWITNfOgirirpexA5kkZtmHk/yD.14QOc006BzllZVyv2")
                .role(Role.USER).build();
        List<UserEntity> users = List.of(userEntity);

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> entities = userRepository.findAll();

        assertEquals(1, entities.size());
        assertEquals("michal.malek98@wp.pl", entities.get(0).getEmail());
    }
}
