package ro.alexk.energyutilityplatformbackend.dtos.user;

import ro.alexk.energyutilityplatformbackend.entities.Credentials;
import ro.alexk.energyutilityplatformbackend.enums.Role;

import java.util.UUID;

public record UserDto(
        UUID id,
        Credentials credentials,
        String name,
        Role role
) {
}
