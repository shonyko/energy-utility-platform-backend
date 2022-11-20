package ro.alexk.energyutilityplatformbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.alexk.energyutilityplatformbackend.annotations.CheckUUID;
import ro.alexk.energyutilityplatformbackend.entities.BaseEntity;
import ro.alexk.energyutilityplatformbackend.mappers.BaseMapper;
import ro.alexk.energyutilityplatformbackend.services.BaseService;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseController<T extends BaseEntity, C, R, U, CTX> {

    private final BaseService<T> service;

    private final BaseMapper<T, C, R, U, CTX> mapper;

    private final Function<UUID, CTX> getUpdateContext;

    // Create
    @PostMapping
    public ResponseEntity<R> create(@RequestBody @Valid C dto) {
        var created = service.create(mapper.map(dto));
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(created).toUri();
        return ResponseEntity.created(location)
                .body(mapper.map(created));
    }

    // Read
    @GetMapping
    public ResponseEntity<Set<R>> getAll() {
        return ResponseEntity.ok(
                mapper.map(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> get(@PathVariable @CheckUUID String id) {
        return ResponseEntity.ok(
                mapper.map(service.findById(id))
        );
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<R> update(@PathVariable @CheckUUID String id, @RequestBody @Valid U dto) {
        var toUpdate = mapper.map(dto, getUpdateContext.apply(UUID.fromString(id)));
        var updated = service.update(toUpdate);
        return ResponseEntity.ok(
                mapper.map(updated)
        );
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @CheckUUID String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
