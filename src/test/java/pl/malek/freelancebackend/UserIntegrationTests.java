package pl.malek.freelancebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldThrowExceptionAboutUserValidation() throws Exception {
        String json = """
                {
                    "email": "adam.nowak@wp.pl"
                """;

        mvc.perform(post("/users/").contentType("application/json").content(json))
                .andExpect(status().isConflict());
    }

}
