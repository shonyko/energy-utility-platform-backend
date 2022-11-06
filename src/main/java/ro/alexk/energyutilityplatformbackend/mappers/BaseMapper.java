package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.Context;
import ro.alexk.energyutilityplatformbackend.entities.BaseEntity;

import java.util.Set;

public interface BaseMapper<T extends BaseEntity, C, R, U> {

    T map(C from);

    R map(T from);

    Set<R> map(Set<T> from);

    T map(U from, @Context UpdateContext context);
}
