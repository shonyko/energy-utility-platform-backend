package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.*;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.Address;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.mappers.context.DeviceUpdateContext;

import java.util.Set;
import java.util.UUID;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, AddressMapper.class}
)
public abstract class DeviceMapper
        implements BaseMapper<Device, DeviceCreateDto, DeviceDto, DeviceUpdateDto, DeviceUpdateContext> {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "addressId", target = "address", qualifiedByName = "addressIdToEntity")
    public abstract Device map(DeviceCreateDto from);

    public abstract DeviceDto map(Device from);

    public abstract Set<DeviceDto> map(Set<Device> from);

    public abstract Device map(DeviceUpdateDto from, @Context DeviceUpdateContext context);

    @Named("addressIdToEntity")
    protected Address addressIdToEntity(UUID addressId) {
        return new Address() {{
            setId(addressId);
        }};
    }
}

