package ro.alexk.energyutilityplatformbackend.dtos.device;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record DeviceCreateDto(

        String description,

        @NotNull
        UUID addressId
) {
}
