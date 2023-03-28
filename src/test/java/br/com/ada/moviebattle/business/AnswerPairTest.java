package br.com.ada.moviebattle.business;

import br.com.ada.moviebattle.business.domain.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnswerPairTest {

    @Test
    @DisplayName("Should return true if answer is correct")
    public void shouldReturnTrueIfAnswerIsCorrect() {
        assertTrue(
                new AnswerPair().checkAnswer(
                        new Movie("1", BigDecimal.ZERO),
                        new Movie("1", new BigDecimal("2872.5")),
                        new Movie("2", new BigDecimal("1875.1"))
                )
        );
    }

    @Test
    @DisplayName("Should return false if answer is incorrect")
    public void shouldReturnFalseIfAnswerIsIncorrect() {
        assertFalse(
                new AnswerPair().checkAnswer(
                        new Movie("2", BigDecimal.ZERO),
                        new Movie("1", new BigDecimal("2872.5")),
                        new Movie("2", new BigDecimal("1875.1"))
                )
        );
    }

    @Test
    @DisplayName("Should return true if movies have the same rate")
    public void shouldReturnTrueIfMoviesHaveTheSameRate() {
        assertTrue(
                new AnswerPair().checkAnswer(
                        new Movie("2", BigDecimal.ZERO),
                        new Movie("1", new BigDecimal("2872.5")),
                        new Movie("2", new BigDecimal("2872.5"))
                )
        );
    }
}
