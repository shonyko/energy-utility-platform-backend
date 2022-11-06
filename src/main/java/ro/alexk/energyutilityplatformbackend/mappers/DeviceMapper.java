package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.*;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.Device;

import java.util.Set;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, AddressMapper.class}
)
public abstract class DeviceMapper implements BaseMapper<Device, DeviceCreateDto, DeviceDto, DeviceUpdateDto> {

    @Mapping(target = "id", ignore = true)
    public abstract Device map(DeviceCreateDto from);

    public abstract DeviceDto map(Device from);

    public abstract Set<DeviceDto> map(Set<Device> from);

    public abstract Device map(DeviceUpdateDto from, @Context UpdateContext context);
}

