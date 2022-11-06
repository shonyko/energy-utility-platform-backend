package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.Credentials;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByCredentials(Credentials credentials);

    Optional<User> findByCredentialsUsername(String username);

    boolean existsByCredentialsUsername(String username);

    boolean existsByCredentialsUsernameAndIdNot(String username, UUID id);
}
