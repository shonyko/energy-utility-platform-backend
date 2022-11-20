package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;
import ro.alexk.energyutilityplatformbackend.repositories.DeviceRepository;
import ro.alexk.energyutilityplatformbackend.services.AddressService;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;
import ro.alexk.energyutilityplatformbackend.services.MeasurementService;

import java.util.Set;
import java.util.UUID;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

    private final DeviceRepository deviceRepository;


    private final AddressService addressService;
    private final MeasurementService measurementService;

    public DeviceServiceImpl(DeviceRepository repository, AddressService addressService, MeasurementService measurementService) {
        super(repository);
        this.deviceRepository = repository;
        this.addressService = addressService;
        this.measurementService = measurementService;
    }

    @Override
    public Device create(Device device) {
        var addr = addressService.findById(device.getAddress());
        device.setAddress(addr);
        return super.create(device);
    }

    @Override
    public Set<Device> findByUserId(String id) {
        return deviceRepository.findByUserId(UUID.fromString(id));
    }

    @Override
    public Set<Device> findAvailable() {
        return deviceRepository.findByUserIsNull();
    }

    @Override
    public Set<Measurement> getMeasurements(String deviceId) {
        checkExists(UUID.fromString(deviceId));
        return measurementService.findByDeviceId(deviceId);
    }

    @Override
    public void addMeasurement(String deviceId, Measurement measurement) {
        var device = findReferenceById(UUID.fromString(deviceId));
        measurement.setDevice(device);
        measurementService.create(measurement);
    }
}
