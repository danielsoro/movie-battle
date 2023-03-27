package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByTitle(final String title);

    @Query("SELECT m FROM Movie m ORDER BY RAND()")
    List<Movie> getMovieRandomly(final Pageable pageable);

    default List<Movie> findTwoMovieRandomly() {
        return this.getMovieRandomly(Pageable.ofSize(2));
    }
}
