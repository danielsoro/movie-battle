package br.com.ada.moviebattle.infra.database.repositories;

import br.com.ada.moviebattle.ClearDatabaseExtension;
import br.com.ada.moviebattle.infra.database.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(ClearDatabaseExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should be able to find User by username and password")
    public void shouldFindUserByUsernameAndPassword() {
        final var password = UUID.randomUUID().toString().substring(0, 30);
        userRepository.save(User.builder()
                .username("danielsoro")
                .password(password)
                .build());

        userRepository.findByUsernameAndPassword("danielsoro@gmail.com", password)
                .map(u -> {
                    assertNotNull(u.getId());
                    assertEquals("danielsoro@gmail.com", u.getUsername());
                    assertEquals(password, u.getPassword());
                    return u;
                });
    }

    @Test
    @DisplayName("Should not accept username greater than 30 chars")
    public void shouldNotAcceptUsernameGreaterThan30Chars() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(User.builder()
                    .username("danielsorodanielsorodanielsorodanielsorodanielsorodanielsorodanielsoro")
                    .password("password")
                    .build());
        });
    }

    @Test
    @DisplayName("Should not accept password greater than 30 chars")
    public void shouldNotAcceptPasswordGreaterThan30Chars() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(User.builder()
                    .username("asdasd")
                    .password("passwordpasswordpasswordpasswordpassword")
                    .build());
        });
    }

    @Test
    @DisplayName("Should not accept duplicated username")
    public void shouldNotAcceptDuplicatedUsername() {
        userRepository.save(User.builder()
                .username("username")
                .password("password")
                .build());
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(User.builder()
                    .username("username")
                    .password("my-password")
                    .build());
        });
    }
}
