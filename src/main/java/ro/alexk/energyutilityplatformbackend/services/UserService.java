package ro.alexk.energyutilityplatformbackend.services;

import ro.alexk.energyutilityplatformbackend.entities.User;

public interface UserService extends BaseService<User> {

    void checkUsernameAvailability(String username);
}
