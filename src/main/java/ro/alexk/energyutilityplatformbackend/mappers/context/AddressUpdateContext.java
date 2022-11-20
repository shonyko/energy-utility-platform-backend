package ro.alexk.energyutilityplatformbackend.mappers.context;

import ro.alexk.energyutilityplatformbackend.entities.Address;

import java.util.UUID;

public class AddressUpdateContext extends UpdateContext<Address> {

    public AddressUpdateContext(UUID id) {
        super(id);
    }
}