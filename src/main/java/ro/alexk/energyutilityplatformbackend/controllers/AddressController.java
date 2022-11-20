package ro.alexk.energyutilityplatformbackend.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.annotations.Admin;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressDto;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.Address;
import ro.alexk.energyutilityplatformbackend.mappers.AddressMapper;
import ro.alexk.energyutilityplatformbackend.mappers.context.AddressUpdateContext;
import ro.alexk.energyutilityplatformbackend.services.AddressService;

@Admin
@Validated
@RestController
@RequestMapping(AddressController.PATH)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController
        extends BaseController<Address, AddressCreateDto, AddressDto, AddressUpdateDto, AddressUpdateContext> {

    public static final String PATH = "/api/addresses";

    public AddressController(AddressService service, AddressMapper mapper) {
        super(service, mapper, AddressUpdateContext::new);
    }
}
