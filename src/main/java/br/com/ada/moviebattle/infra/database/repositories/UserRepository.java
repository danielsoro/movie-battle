package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.infra.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(final String username);
    Optional<User> findByUsernameAndPassword(final String username, final String password);
}
