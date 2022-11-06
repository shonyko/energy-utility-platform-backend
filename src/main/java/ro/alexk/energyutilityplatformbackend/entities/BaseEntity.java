package ro.alexk.energyutilityplatformbackend.entities;

import java.util.UUID;

public interface BaseEntity {

    UUID getId();

    void setId(UUID id);
}
