package br.com.ada.moviebattle.business;

import br.com.ada.moviebattle.infra.database.entities.Pair;
import br.com.ada.moviebattle.infra.database.entities.QuizUser;
import br.com.ada.moviebattle.infra.database.repositories.MovieRepository;
import br.com.ada.moviebattle.infra.database.repositories.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryMoviePair {


    private final MovieRepository movieRepository;
    private final PairRepository pairRepository;

    public FactoryMoviePair(@Autowired final MovieRepository movieRepository,
                            @Autowired final PairRepository pairRepository) {
        this.movieRepository = movieRepository;
        this.pairRepository = pairRepository;
    }

    public Pair generateNewPairFor(final QuizUser quizUser) {
        final var twoMovieRandomly = movieRepository.findTwoMovieRandomly();

        return pairRepository.save(Pair.builder()
                .quizUser(quizUser)
                .movieOne(twoMovieRandomly.get(0))
                .movieTwo(twoMovieRandomly.get(1))
                .build());
    }
}
