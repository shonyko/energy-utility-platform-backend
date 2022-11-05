package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/users";

    @GetMapping
    public ResponseEntity<String> index(Authentication auth) {
        return ResponseEntity.ok(auth.getAuthorities().toString());
    }
}
