package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.ClearDatabaseExtension;
import br.com.ada.moviebattle.infra.database.entities.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(ClearDatabaseExtension.class)
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

    @Test
    @DisplayName("Should be able to find two movie randomly")
    public void shouldBeAbleToFindMovieRandomly() {

        IntStream.range(0, 50).forEach(value ->
                movieRepository.save(Movie.builder()
                        .title(String.format("%s-%d","Movie", value))
                        .rate(new BigDecimal("9.5"))
                        .build())
        );

        final var oneMovieRandomly = movieRepository.findTwoMovieRandomly();
        assertFalse(oneMovieRandomly.isEmpty());
        assertEquals(2, oneMovieRandomly.size());
        assertNotNull(oneMovieRandomly.get(0).getId());
        assertNotNull(oneMovieRandomly.get(0).getTitle());
        assertNotNull(oneMovieRandomly.get(1).getId());
        assertNotNull(oneMovieRandomly.get(1).getTitle());
    }

}
