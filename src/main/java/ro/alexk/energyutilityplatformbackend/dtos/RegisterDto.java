package ro.alexk.energyutilityplatformbackend.dtos;

import ro.alexk.energyutilityplatformbackend.enums.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record RegisterDto(
        @NotNull @NotEmpty @NotBlank
        String name,
        Role role,
        @Valid
        CredentialsDto credentials
) {
}
