package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairRepository extends JpaRepository<Pair, String> {
}
