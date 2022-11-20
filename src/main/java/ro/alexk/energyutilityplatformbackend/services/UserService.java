package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.Set;

public interface UserService extends BaseService<User> {

    void checkUsernameAvailability(String username);

    Set<Device> getDevices(String userId);

    void addDevice(String userId, String deviceId);

    void removeDevice(String userId, String deviceId);
}
