package ro.alexk.energyutilityplatformbackend.dtos.measurement;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record MeasurementCreateDto(

        @NotNull
        LocalDateTime timeStamp,

        @NotNull
        Double energyConsumption
) {
}
