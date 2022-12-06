package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.enums.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByRole(Role role);

    Optional<User> findByCredentialsUsername(String username);

    boolean existsByCredentialsUsername(String username);

    boolean existsByCredentialsUsernameAndIdNot(String username, UUID id);
}
