package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.Device;

import java.util.Set;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    Set<Device> findByUserId(UUID id);

    Set<Device> findByUserIsNull();
}
