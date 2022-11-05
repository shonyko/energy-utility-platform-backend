package ro.alexk.energyutilityplatformbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
