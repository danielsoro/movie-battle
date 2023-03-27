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
    @DisplayName("Should get movie from OMDb API by Title")
    public void shouldGetMovieFromOMDbApiByTitle() {
        final var movie = OMDbClient.getMovieByTitle(apiKey, "Batman");
        assertNotNull(movie.getTitle());
        assertNotNull(movie.getRating());
        assertNotNull(movie.getId());
    }

    @Test
    @DisplayName("Should get movie from OMDb API by Id")
    public void shouldGetMovieFromOMDbApiById() {
        final var movie = OMDbClient.getMovieById(apiKey, "tt2911666");
        assertNotNull(movie.getTitle());
        assertNotNull(movie.getRating());
        assertNotNull(movie.getId());
    }

    @Test
    @DisplayName("Should get list of movies from OMDb API")
    public void shouldSearchMovieFromOMDbApi() {
        final var search = OMDbClient.searchMovies(apiKey, "Batman");
        assertNotNull(search.getSearch().iterator().next().getTitle());
    }
}
