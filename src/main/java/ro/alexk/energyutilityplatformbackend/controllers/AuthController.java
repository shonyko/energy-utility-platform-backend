package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ro.alexk.energyutilityplatformbackend.dtos.auth.AuthDto;
import ro.alexk.energyutilityplatformbackend.dtos.auth.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.dtos.auth.RegisterDto;
import ro.alexk.energyutilityplatformbackend.mappers.AuthMapper;
import ro.alexk.energyutilityplatformbackend.services.AuthService;
import ro.alexk.energyutilityplatformbackend.services.JwtService;
import ro.alexk.energyutilityplatformbackend.services.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserService userService;

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
                authService.register(mapper.map(registerDto))
        ));
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthDto> getAuth(Authentication auth) {
        if (!auth.isAuthenticated()) return ResponseEntity.notFound().build();
        var jwt = (Jwt) auth.getPrincipal();
        var user = userService.findById(jwt.getSubject());
        return ResponseEntity.ok(mapper.map(user));
    }
}
