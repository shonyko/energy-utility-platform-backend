package ro.alexk.energyutilityplatformbackend.dtos.auth;

import ro.alexk.energyutilityplatformbackend.enums.Role;

import java.util.UUID;

public record AuthDto(

        UUID id,
        String name,
        Role role
) {
}
