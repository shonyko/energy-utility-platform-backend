package ro.alexk.energyutilityplatformbackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.alexk.energyutilityplatformbackend.dtos.auth.AuthDto;
import ro.alexk.energyutilityplatformbackend.dtos.auth.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.dtos.auth.RegisterDto;
import ro.alexk.energyutilityplatformbackend.entities.Credentials;
import ro.alexk.energyutilityplatformbackend.entities.User;

import javax.annotation.Resource;

@Mapper(
        componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AuthMapper {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Named("fromCredentialsDto")
    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract Credentials map(CredentialsDto credentialsDto);

    @Mapping(source = "credentials", target = "credentials", qualifiedByName = "fromCredentialsDto")
    public abstract User map(RegisterDto registerDto);

    public abstract AuthDto map(User user);

    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
