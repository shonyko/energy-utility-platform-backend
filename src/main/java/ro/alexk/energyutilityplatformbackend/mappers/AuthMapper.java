package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.alexk.energyutilityplatformbackend.dtos.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.dtos.RegisterDto;
import ro.alexk.energyutilityplatformbackend.entities.Credentials;
import ro.alexk.energyutilityplatformbackend.entities.User;

import javax.annotation.Resource;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AuthMapper {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Named("fromCredentialsDto")
    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract Credentials from(CredentialsDto credentialsDto);

    @Mapping(source = "credentials", target = "credentials", qualifiedByName = "fromCredentialsDto")
    public abstract User from(RegisterDto registerDto);

    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
