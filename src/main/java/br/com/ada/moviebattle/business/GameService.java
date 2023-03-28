package br.com.ada.moviebattle.business;

import br.com.ada.moviebattle.infra.database.entities.User;
import br.com.ada.moviebattle.infra.database.repositories.QuizUserRepository;
import br.com.ada.moviebattle.infra.database.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final UserRepository userRepository;
    private final QuizUserRepository quizUserRepository;

    public GameService(final UserRepository userRepository, final QuizUserRepository quizUserRepository) {
        this.userRepository = userRepository;
        this.quizUserRepository = quizUserRepository;
    }

    public void start(final String username) {
        userRepository.findByUsername(username).map(u -> validateQuizUser(u, false))
                .orElseThrow(() -> new RuntimeException());
    }

    public void stop(final String username) {
        userRepository.findByUsername(username).map(u -> validateQuizUser(u, true))
                .orElseThrow(() -> new RuntimeException());
    }

    private User validateQuizUser(final User user, final boolean enable) {
        if (quizUserRepository.findByUserAndEnabledIsTrue(user).isPresent() != enable) {
            throw new RuntimeException();
        }
        return user;
    }
}
