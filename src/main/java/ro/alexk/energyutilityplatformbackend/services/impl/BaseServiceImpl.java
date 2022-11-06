package ro.alexk.energyutilityplatformbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.exceptions.ResourceNotFoundException;
import ro.alexk.energyutilityplatformbackend.services.BaseService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private final JpaRepository<T, UUID> repository;

    @Override
    public T create(T T) {
        return repository.save(T);
    }

    @Override
    public T findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public T update(T T) {
        return repository.save(T);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
