package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementDto;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.util.Set;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {DeviceMapper.class}
)
public abstract class MeasurementMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Measurement map(MeasurementCreateDto from);

    public abstract MeasurementDto map(Measurement from);

    public abstract Set<MeasurementDto> map(Set<Measurement> from);
}
