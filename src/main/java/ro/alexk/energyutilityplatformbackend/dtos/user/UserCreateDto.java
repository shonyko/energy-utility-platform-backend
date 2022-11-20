package ro.alexk.energyutilityplatformbackend.dtos.user;

import ro.alexk.energyutilityplatformbackend.dtos.auth.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.enums.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        CredentialsDto credentials,

        @NotNull @NotEmpty @NotBlank
        String name,

        Role role
) {
}