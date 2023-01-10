package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(DebugController.PATH)
public class DebugController {

    public static final String PATH = "/debug";

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello world!");
    }
}
