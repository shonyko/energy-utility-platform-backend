package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.Address;
import ro.alexk.energyutilityplatformbackend.repositories.AddressRepository;
import ro.alexk.energyutilityplatformbackend.services.AddressService;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {

    public AddressServiceImpl(AddressRepository repository) {
        super(repository);
    }
}
