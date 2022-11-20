package ro.alexk.energyutilityplatformbackend.dtos.measurement;

import java.time.LocalDateTime;
import java.util.UUID;

public record MeasurementDto(

        UUID id,
        LocalDateTime timeStamp,
        Double energyConsumption
) {
}
