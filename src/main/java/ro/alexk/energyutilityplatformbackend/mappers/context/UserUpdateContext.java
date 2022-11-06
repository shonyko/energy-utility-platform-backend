package ro.alexk.energyutilityplatformbackend.mappers.context;

import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.UUID;

public class UserUpdateContext extends UpdateContext<User> {

    public UserUpdateContext(UUID id) {
        super(id);
    }
}