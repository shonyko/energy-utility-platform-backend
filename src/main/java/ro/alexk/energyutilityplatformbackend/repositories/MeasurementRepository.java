package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.util.Set;
import java.util.UUID;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {

    Set<Measurement> findByDeviceId(UUID deviceId);
}
