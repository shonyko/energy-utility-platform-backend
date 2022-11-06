package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.User;

public interface AuthService {

    User login(String username, String password);

    User register(User user);
}
