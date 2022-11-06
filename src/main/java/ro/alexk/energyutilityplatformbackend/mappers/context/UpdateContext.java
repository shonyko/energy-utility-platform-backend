package ro.alexk.energyutilityplatformbackend.mappers.context;

import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import ro.alexk.energyutilityplatformbackend.entities.BaseEntity;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateContext<T extends BaseEntity> {

    private final UUID id;

    @AfterMapping
    public void setId(@MappingTarget T T) {
        T.setId(id);
    }
}
