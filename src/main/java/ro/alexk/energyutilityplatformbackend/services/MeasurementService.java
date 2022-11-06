package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.util.Set;

public interface MeasurementService extends BaseService<Measurement> {

    Set<Measurement> findByDeviceId(String deviceId);
}
