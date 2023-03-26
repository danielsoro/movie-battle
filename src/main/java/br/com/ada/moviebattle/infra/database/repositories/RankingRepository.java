package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, String> {
}
