package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.util.Set;

public interface DeviceService extends BaseService<Device> {

    Set<Device> findByUserId(String id);

    Set<Device> findAvailable();

    Set<Measurement> getMeasurements(String deviceId);

    void addMeasurement(String deviceId, Measurement measurement);
}
