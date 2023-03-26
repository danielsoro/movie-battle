package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Should not be able to persist two movies with same title")
    public void shouldNotBeAbleToPersistTwoMoviesWithSameTitle() {
        movieRepository.save(Movie.builder()
                .title("Movie 1")
                .rate(new BigDecimal("9.5"))
                .build());

        assertThrows(DataIntegrityViolationException.class, () -> {
            movieRepository.save(Movie.builder()
                    .title("Movie 1")
                    .rate(new BigDecimal("9.5"))
                    .build());
        });
    }

    @Test
    @DisplayName("Should be able to find movie by title")
    public void shouldBeAbleToFindMovieByTitle() {
        final var movie = movieRepository.save(Movie.builder()
                .title("My movie title")
                .rate(new BigDecimal("9.5"))
                .build());

        movieRepository.findByTitle("My movie title")
                .map(m -> {
                    assertNotNull(m.getTitle());
                    assertEquals("My movie title", m.getTitle());
                    assertEquals(new BigDecimal("9.5"), movie.getRate());
                    return m;
                });
    }

}
