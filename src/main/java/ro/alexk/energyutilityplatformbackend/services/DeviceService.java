package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.Set;

public interface DeviceService extends BaseService<Device> {

    Set<Device> findByUser(User user);
}
