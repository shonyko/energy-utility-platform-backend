package ro.alexk.energyutilityplatformbackend.dtos.device;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record DeviceUpdateDto(

        String description,

        @NotNull
        UUID addressId,
        
        @NotNull @Min(0)
        Double maxHourlyConsumption
) {
}
