package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.dtos.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.dtos.RegisterDto;
import ro.alexk.energyutilityplatformbackend.mappers.AuthMapper;
import ro.alexk.energyutilityplatformbackend.services.AuthService;
import ro.alexk.energyutilityplatformbackend.services.JwtService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    private final AuthMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDto userLogin) {
        return ResponseEntity.ok(jwtService.generateToken(
                authService.login(userLogin.username(), userLogin.password())
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto) {
        return ResponseEntity.ok(jwtService.generateToken(
                authService.register(mapper.from(registerDto))
        ));
    }
}
