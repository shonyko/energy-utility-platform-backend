package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.*;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.mappers.context.UserUpdateContext;

import java.util.Set;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper implements BaseMapper<User, UserCreateDto, UserDto, UserUpdateDto, UserUpdateContext> {

    @Mapping(target = "id", ignore = true)
    public abstract User map(UserCreateDto from);

    public abstract UserDto map(User from);

    public abstract Set<UserDto> map(Set<User> from);

    public abstract User map(UserUpdateDto from, @Context UserUpdateContext context);
}
