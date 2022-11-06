package ro.alexk.energyutilityplatformbackend.mappers.context;

import ro.alexk.energyutilityplatformbackend.entities.Device;

import java.util.UUID;

public class DeviceUpdateContext extends UpdateContext<Device> {

    public DeviceUpdateContext(UUID id) {
        super(id);
    }
}