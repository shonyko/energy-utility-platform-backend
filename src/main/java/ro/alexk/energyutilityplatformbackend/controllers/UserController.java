package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.alexk.energyutilityplatformbackend.annotations.Admin;
import ro.alexk.energyutilityplatformbackend.annotations.CheckUUID;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserUpdateDto;
import ro.alexk.energyutilityplatformbackend.mappers.UpdateContext;
import ro.alexk.energyutilityplatformbackend.mappers.UserMapper;
import ro.alexk.energyutilityplatformbackend.services.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;
import java.util.UUID;

@Admin
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/api/users";

    private final UserService userService;

    private final UserMapper mapper;

    // Create
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateDto dto) {
        var created = userService.create(mapper.map(dto));
        var location = String.format("%s/%s", PATH, created.getId());
        return ResponseEntity.created(URI.create(location))
                .body(mapper.map(created));
    }

    // Read
    @GetMapping
    public ResponseEntity<Set<UserDto>> getAll() {
        return ResponseEntity.ok(
                mapper.map(userService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable @CheckUUID String id) {
        return ResponseEntity.ok(
                mapper.map(userService.findById(id))
        );
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable @CheckUUID String id, @RequestBody @Valid UserUpdateDto dto) {
        var toUpdate = mapper.map(dto, new UpdateContext(UUID.fromString(id)));
        var updated = userService.update(toUpdate);
        return ResponseEntity.ok(
                mapper.map(updated)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @CheckUUID String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
