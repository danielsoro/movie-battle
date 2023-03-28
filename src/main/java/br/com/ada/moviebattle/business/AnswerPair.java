package br.com.ada.moviebattle.business;

import br.com.ada.moviebattle.business.domain.Movie;
import org.springframework.stereotype.Service;

@Service
public class AnswerPair {

    public boolean checkAnswer(final Movie answer, final Movie movieOne, final Movie movieTwo) {
        final var compare = movieOne.points().compareTo(movieTwo.points());

        if (compare == 0) {
            return true;
        }

        return compare > 0 && answer.id().equalsIgnoreCase(movieOne.id());
    }
}
