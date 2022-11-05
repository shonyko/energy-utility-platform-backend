package ro.alexk.energyutilityplatformbackend.repositories;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.Set;

public interface DeviceRepository extends ExtendedJpaRepository<Device> {

    Set<Device> findByUser(User user);
}