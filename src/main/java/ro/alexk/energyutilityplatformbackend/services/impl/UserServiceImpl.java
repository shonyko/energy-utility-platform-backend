package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.exceptions.ResourceNotFoundException;
import ro.alexk.energyutilityplatformbackend.repositories.UserRepository;
import ro.alexk.energyutilityplatformbackend.services.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repository) {
        super(repository);
        userRepository = repository;
    }

    @Override
    public User create(User user) {
        checkUsernameAvailability(user.getUsername());
        return super.create(user);
    }

    @Override
    public User update(User user) {
        if (!userRepository.existsById(user.getId())) throw new ResourceNotFoundException(NOT_FOUND_MESSAGE);
        if (userRepository.existsByCredentialsUsernameAndIdNot(user.getUsername(), user.getId()))
            throw new DataIntegrityViolationException("Username already exists!");

        return super.update(user);
    }

    @Override
    public void checkUsernameAvailability(String username) {
        if (!userRepository.existsByCredentialsUsername(username)) return;
        throw new DataIntegrityViolationException("Username already exists!");
    }
}
