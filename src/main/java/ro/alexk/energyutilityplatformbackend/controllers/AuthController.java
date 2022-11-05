package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.dtos.CredentialsDto;
import ro.alexk.energyutilityplatformbackend.services.JwtService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDto userLogin) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password())
        );
        return ResponseEntity.ok(jwtService.generateToken(auth));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("");
    }
}
