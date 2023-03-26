package br.com.ada.moviebattle.infra.http.client.omdb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableConfigurationProperties
class IMBDClientTest {

    @Value("${br.com.ada.moviebattle.omdb.key}")
    private String apiKey;

    @Autowired
    private OMDbClient OMDbClient;

    @Test
    @DisplayName("Should get movie from OMDb API")
    public void shouldGetMovieFromOMDbApi() {
        final var batmam = OMDbClient.getMovie(apiKey, "Batman");
        assertNotNull(batmam.getTitle());
    }

    @Test
    @DisplayName("Should get list of movies from OMDb API")
    public void shouldSearchMovieFromOMDbApi() {
        final var search = OMDbClient.searchMovies(apiKey, "Batman");
        assertNotNull(search.getSearch().iterator().next().getTitle());
    }
}
