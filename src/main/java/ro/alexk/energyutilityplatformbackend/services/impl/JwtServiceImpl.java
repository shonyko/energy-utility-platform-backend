package ro.alexk.energyutilityplatformbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.security.SecurityProperties;
import ro.alexk.energyutilityplatformbackend.services.JwtService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final SecurityProperties securityProperties;
    private final JwtEncoder encoder;

    @Override
    public String generateToken(User user) {
        var authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return generateToken(authorities, user.getId().toString());
    }

    private String generateToken(List<String> authorities, String subject) {
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(securityProperties.expiryTime(), ChronoUnit.MILLIS))
                .subject(subject)
                .claim(securityProperties.claimName(), authorities)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
