package pl.malek.freelancebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.malek.freelancebackend.dto.User;
import pl.malek.freelancebackend.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeAll() {
        userRepository.deleteAll();
    }

    @AfterEach
    void afterAll() {
        userRepository.deleteAll();
    }

    @Test
    void shouldThrowExceptionAboutUserValidation() throws Exception {
        User user = User.builder().email("adam.nowak@wp.pl").build();
        mvc.perform(post("/users/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldProperlySaveUser() throws Exception {
        User user = User.builder()
                .email("test.test@gmail.com")
                .password("Alamakota1.")
                .firstName("test")
                .lastName("test")
                .build();
        mvc.perform(post("/users/").contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }
}
