package ro.alexk.energyutilityplatformbackend.mappers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import ro.alexk.energyutilityplatformbackend.entities.User;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateContext {

    private final UUID id;

    @AfterMapping
    public void setId(@MappingTarget User user) {
        user.setId(id);
    }
}
