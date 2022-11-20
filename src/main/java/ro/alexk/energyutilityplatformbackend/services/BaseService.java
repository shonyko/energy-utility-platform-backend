package ro.alexk.energyutilityplatformbackend.services;

import java.util.Set;
import java.util.UUID;

public interface BaseService<T> {

    String NOT_FOUND_MESSAGE = "Resource not found";

    T create(T T);

    T findById(UUID id);

    T findById(String id);

    T findById(T T);

    Set<T> findAll();

    T update(T T);

    void delete(String id);

    T findReferenceById(UUID id);
}
