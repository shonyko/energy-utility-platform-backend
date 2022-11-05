package ro.alexk.energyutilityplatformbackend.services;

import java.util.Set;

public interface BaseService<T> {

    String NOT_FOUND_MESSAGE = "Resource not found";

    T create(T T);

    T findById(String id);

    Set<T> findAll();

    T update(T T);

    void delete(String id);
}
