package ro.alexk.energyutilityplatformbackend.repositories;

import ro.alexk.energyutilityplatformbackend.entities.Credentials;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.Optional;

public interface UserRepository extends ExtendedJpaRepository<User> {

    Optional<User> getByCredentials(Credentials credentials);
}
