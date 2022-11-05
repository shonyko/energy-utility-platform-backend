package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface ExtendedJpaRepository<T> extends JpaRepository<T, UUID> {

    void deleteById(@Nullable UUID id);
}
