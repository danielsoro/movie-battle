package br.com.ada.moviebattle.infra.database.seed;

import br.com.ada.moviebattle.infra.database.repositories.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = {"br.com.ada.moviebattle.omdb.seed=true"})
class MovieSeedTest {


    @Autowired
    private MovieSeed movieSeed;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Should populate movies from OMDb")
    public void populateMovies() {
        final var allMovies = movieRepository.findAll();
        assertTrue(allMovies.size() > 1);
    }

}
