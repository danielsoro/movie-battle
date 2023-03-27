package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.QuizUser;
import br.com.ada.moviebattle.infra.database.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, String> {
    int countByEnabledIsTrue();

    @EntityGraph(attributePaths = {"pairs"})
    Optional<QuizUser> findByUserAndEnabledIsTrue(final User user);
}
