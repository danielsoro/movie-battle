package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.QuizUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, String> {
    int countByEnabledIsTrue();
}
