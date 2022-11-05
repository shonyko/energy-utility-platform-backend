package ro.alexk.energyutilityplatformbackend.services;

import org.springframework.security.core.Authentication;

public interface JwtService {

    String generateToken(Authentication auth);
}
