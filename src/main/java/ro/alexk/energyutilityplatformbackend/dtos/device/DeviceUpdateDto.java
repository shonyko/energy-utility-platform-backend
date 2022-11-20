package ro.alexk.energyutilityplatformbackend.dtos.device;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record DeviceUpdateDto(

        String description,

        @NotNull
        String addressId,

        @NotNull @Min(0)
        Double maxHourlyConsumption
) {
}
