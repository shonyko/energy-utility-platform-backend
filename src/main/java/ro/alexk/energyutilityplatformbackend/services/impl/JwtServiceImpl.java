package ro.alexk.energyutilityplatformbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.security.SecurityProperties;
import ro.alexk.energyutilityplatformbackend.services.JwtService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final SecurityProperties securityProperties;
    private final JwtEncoder encoder;

    @Override
    public String generateToken(Authentication auth) {
        var now = Instant.now();

        var authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(securityProperties.expiryTime(), ChronoUnit.MILLIS))
                .subject(auth.getName())
                .claim(securityProperties.claimName(), authorities)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
