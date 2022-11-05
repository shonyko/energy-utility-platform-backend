package ro.alexk.energyutilityplatformbackend.repositories;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.util.Set;

public interface MeasurementRepository extends ExtendedJpaRepository<Measurement> {

    Set<Measurement> getByDevice(Device device);
}
