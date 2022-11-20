package ro.alexk.energyutilityplatformbackend.dtos.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CredentialsDto(
        @NotNull @NotEmpty @NotBlank
        String username,
        @NotNull @NotEmpty @NotBlank
        String password
) {
}
