package ro.alexk.energyutilityplatformbackend.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.services.AuthService;
import ro.alexk.energyutilityplatformbackend.services.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserService userService;

    @Override
    public User login(String username, String password) {
        var auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return (User) auth.getPrincipal();
    }

    @Override
    public User register(User user) {
        if (userService.usernameExists(user.getUsername()))
            throw new DataIntegrityViolationException("Username already exists!");
        return userService.create(user);
    }
}
