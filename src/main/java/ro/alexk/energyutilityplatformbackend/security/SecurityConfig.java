package ro.alexk.energyutilityplatformbackend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConstructorBinding
@ConfigurationProperties(prefix = "security")
public record SecurityConfig(
        @NotNull @NotEmpty @NotBlank
        String secret,

        @NotNull
        Long expiryTime,

        @NotNull @NotEmpty @NotBlank
        String tokenPrefix
) {
}
