package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.repositories.DeviceRepository;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;

import java.util.Set;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository repository) {
        super(repository);
        this.deviceRepository = repository;
    }

    @Override
    public Set<Device> findByUser(User user) {
        return deviceRepository.findByUser(user);
    }
}
