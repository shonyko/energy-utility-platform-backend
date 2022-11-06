package ro.alexk.energyutilityplatformbackend.dtos.address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AddressUpdateDto(

        @NotNull @NotEmpty @NotBlank
        String name
) {
}
