package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.annotations.Admin;

@RestController
@RequiredArgsConstructor
@RequestMapping(AdminController.PATH)
@Admin
public class AdminController {

    public static final String PATH = "/admins";

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello admin");
    }
}
