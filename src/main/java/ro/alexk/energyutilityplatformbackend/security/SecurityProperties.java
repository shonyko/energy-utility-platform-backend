package ro.alexk.energyutilityplatformbackend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConstructorBinding
@ConfigurationProperties(prefix = "security")
public record SecurityProperties(

        RSAPrivateKey privateKey,

        RSAPublicKey publicKey,

        @NotNull
        Long expiryTime,

        @NotNull @NotEmpty @NotBlank
        String tokenPrefix,

        @NotNull @NotEmpty @NotBlank
        String claimName
) {
}
