package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class IdMapper {

    public abstract String map(UUID from);

    public abstract UUID map(String from);
}
