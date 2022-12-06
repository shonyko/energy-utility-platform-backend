package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.enums.Role;

import java.util.Set;

public interface UserService extends BaseService<User> {

    Set<User> findByRole(Role role);

    void checkUsernameAvailability(String username);

    Set<Device> getDevices(String userId);

    void addDevice(String userId, String deviceId);

    void removeDevice(String userId, String deviceId);
}
