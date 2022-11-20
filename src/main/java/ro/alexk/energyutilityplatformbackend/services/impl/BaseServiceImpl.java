package ro.alexk.energyutilityplatformbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexk.energyutilityplatformbackend.entities.BaseEntity;
import ro.alexk.energyutilityplatformbackend.services.BaseService;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private final JpaRepository<T, UUID> repository;

    @Override
    public T create(T T) {
        return repository.save(T);
    }

    @Override
    public T findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public T findById(String id) {
        return findById(UUID.fromString(id));
    }

    @Override
    public T findById(T T) {
        return findById(T.getId());
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public T update(T T) {
        checkExists(T.getId());
        return repository.save(T);
    }

    @Override
    public void delete(String id) {
        var uuid = UUID.fromString(id);
        if (!repository.existsById(uuid)) return;
        repository.deleteById(uuid);
    }

    @Override
    public T findReferenceById(UUID id) {
        return repository.getReferenceById(id);
    }

    // Utils
    protected void checkExists(UUID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException(NOT_FOUND_MESSAGE);
    }
}
