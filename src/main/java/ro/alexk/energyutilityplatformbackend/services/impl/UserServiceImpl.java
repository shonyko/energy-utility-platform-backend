package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.repositories.UserRepository;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;
import ro.alexk.energyutilityplatformbackend.services.UserService;

import javax.persistence.EntityExistsException;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    private final DeviceService deviceService;

    public UserServiceImpl(UserRepository repository, DeviceService deviceService) {
        super(repository);
        this.userRepository = repository;
        this.deviceService = deviceService;
    }

    @Override
    public User create(User user) {
        checkUsernameAvailability(user.getUsername());
        return super.create(user);
    }

    @Override
    public User update(User user) {
        if (userRepository.existsByCredentialsUsernameAndIdNot(user.getUsername(), user.getId()))
            throw new EntityExistsException("Username already exists!");
        return super.update(user);
    }

    @Override
    public void checkUsernameAvailability(String username) {
        if (!userRepository.existsByCredentialsUsername(username)) return;
        throw new EntityExistsException("Username already exists!");
    }

    @Override
    public Set<Device> getDevices(String userId) {
        checkExists(UUID.fromString(userId));
        return deviceService.findByUserId(userId);
    }

    @Override
    public void addDevice(String userId, String deviceId) {
        var user = findReferenceById(UUID.fromString(userId));
        addOrRemoveDevice(user, deviceId);
    }

    @Override
    public void removeDevice(String userId, String deviceId) {
        checkExists(UUID.fromString(userId));
        addOrRemoveDevice(null, deviceId);
    }

    private void addOrRemoveDevice(User user, String deviceId) {
        var device = deviceService.findById(deviceId);
        device.setUser(user);
        deviceService.update(device);
    }

    @Override
    public void delete(String id) {
        var devices = deviceService.findByUserId(id);
        devices.forEach(device -> {
            device.setUser(null);
            deviceService.update(device);
        });
        super.delete(id);
    }
}
