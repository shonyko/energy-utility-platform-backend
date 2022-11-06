package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.*;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressDto;
import ro.alexk.energyutilityplatformbackend.dtos.address.AddressUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.Address;
import ro.alexk.energyutilityplatformbackend.mappers.context.AddressUpdateContext;

import java.util.Set;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AddressMapper
        implements BaseMapper<Address, AddressCreateDto, AddressDto, AddressUpdateDto, AddressUpdateContext> {

    @Mapping(target = "id", ignore = true)
    public abstract Address map(AddressCreateDto from);

    public abstract AddressDto map(Address from);

    public abstract Set<AddressDto> map(Set<Address> from);

    public abstract Address map(AddressUpdateDto from, @Context AddressUpdateContext context);
}
