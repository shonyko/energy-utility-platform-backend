package ro.alexk.energyutilityplatformbackend.services.impl;

import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.User;
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
    public boolean usernameExists(String username) {
        return userRepository.existsByCredentialsUsername(username);
    }
}
