package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.*;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementDto;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementMQDto;
import ro.alexk.energyutilityplatformbackend.entities.Measurement;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    @Mapping(target = "energyConsumption", source = "measurement_value")
    @Mapping(target = "timeStamp", source = "timestamp", qualifiedByName = "convertTimestamp")
    public abstract Measurement map(MeasurementMQDto from);

    @Named("convertTimestamp")
    protected LocalDateTime convertTimestamp(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZonedDateTime.now().getZone());
    }
}
