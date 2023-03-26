package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByTitle(String title);
}
