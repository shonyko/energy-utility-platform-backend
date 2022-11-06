package ro.alexk.energyutilityplatformbackend.dtos.device;

import ro.alexk.energyutilityplatformbackend.dtos.address.AddressDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserDto;

import java.util.UUID;

public record DeviceDto(

        UUID id,
        String description,
        AddressDto address,
        UserDto user
) {
}
