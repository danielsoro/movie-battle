package br.com.ada.moviebattle.infra.http;

import br.com.ada.moviebattle.ClearDatabaseExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = {"game-controller-test.sql"})
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("user-a")
    @DisplayName("Should be able to start a game")
    public void shouldBeAbleToStartAGame() throws Exception {
        this.mockMvc.perform(get("/battle/start"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("user-b")
    @DisplayName("Should be able to stop a game")
    public void shouldBeAbleToStopAGame() throws Exception {
        this.mockMvc.perform(get("/battle/stop"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("user-b")
    @DisplayName("Should not be able to start a game")
    public void shouldNotBeAbleToStartAGame() throws Exception {
        this.mockMvc.perform(get("/battle/start"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("user-a")
    @DisplayName("Should not be able to stop a game")
    public void shouldNotBeAbleToStopAGame() throws Exception {
        this.mockMvc.perform(get("/battle/stop"))
                .andExpect(status().isBadRequest());
    }
}
