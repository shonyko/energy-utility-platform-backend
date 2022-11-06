package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.User;

public interface JwtService {

    String generateToken(User user);
}
