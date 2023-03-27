package br.com.ada.moviebattle;

import br.com.ada.moviebattle.infra.database.entities.User;
import br.com.ada.moviebattle.infra.database.repositories.QuizUserRepository;
import br.com.ada.moviebattle.infra.database.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Sql({"movies.sql"})
@SpringBootTest
@ExtendWith(ClearDatabaseExtension.class)
class FactoryMoviePairTest {

    @Autowired
    private FactoryMoviePair factoryMoviePair;

    @Autowired
    private QuizUserRepository quizUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should return a pair of movies for an user quiz")
    public void shouldReturnAPairOfMoviesForAnUserQuiz() {
        final var foo = userRepository.findOne(Example.of(User.builder().username("foo").build()));
        final var pair = factoryMoviePair.generateNewPairFor(
                quizUserRepository.findByUserAndEnabledIsTrue(foo.get()).get()
        );

        assertNotNull(pair.getMovieOne());
        assertNotNull(pair.getMovieTwo());
        assertNotNull(pair.getId());
    }

}
