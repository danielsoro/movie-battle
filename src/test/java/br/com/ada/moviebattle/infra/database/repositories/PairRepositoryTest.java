package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Movie;
import br.com.ada.moviebattle.infra.database.entities.Pair;
import br.com.ada.moviebattle.infra.database.entities.Quiz;
import br.com.ada.moviebattle.infra.database.entities.QuizUser;
import br.com.ada.moviebattle.infra.database.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PairRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizUserRepository quizUserRepository;

    @Autowired
    private PairRepository pairRepository;

    @Test
    @DisplayName("Should not accept duplicated pair for a quiz")
    public void shouldNotAcceptDuplicatedPairForAQuiz() {
        final var user = userRepository.save(User.builder()
                .username("asd")
                .password("asdad")
                .build());

        final var movieOne = movieRepository.save(Movie.builder()
                .title("Movie A")
                .rate(new BigDecimal("9.5"))
                .build());

        final var movieTwo = movieRepository.save(Movie.builder()
                .title("Movie B")
                .rate(new BigDecimal("9.5"))
                .build());

        final var quiz = quizRepository.save(Quiz.builder()
                .build());

        final var quizUser = quizUserRepository.save(QuizUser.builder()
                .enabled(true)
                .quiz(quiz)
                .user(user)
                .build());

        assertThrows(DataIntegrityViolationException.class, () -> {
            pairRepository.save(Pair.builder()
                    .movieOne(movieOne)
                    .movieTwo(movieTwo)
                    .quizUser(quizUser)
                    .build());

            pairRepository.save(Pair.builder()
                    .movieOne(movieOne)
                    .movieTwo(movieTwo)
                    .quizUser(quizUser)
                    .build());
        });
    }
}
