package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;
import ro.alexk.energyutilityplatformbackend.repositories.MeasurementRepository;
import ro.alexk.energyutilityplatformbackend.services.MeasurementService;

import java.util.Set;

@Service
public class MeasurementServiceImpl extends BaseServiceImpl<Measurement> implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository repository) {
        super(repository);
        this.measurementRepository = repository;
    }

    @Override
    public Set<Measurement> findByDevice(Device device) {
        return measurementRepository.getByDevice(device);
    }
}
