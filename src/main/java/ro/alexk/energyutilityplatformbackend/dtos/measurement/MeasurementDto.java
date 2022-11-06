package ro.alexk.energyutilityplatformbackend.dtos.measurement;

import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MeasurementDto(

        UUID id,
        LocalDateTime timeStamp,
        Double energyConsumption,
        DeviceDto device
) {
}
