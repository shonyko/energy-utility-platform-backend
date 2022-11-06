package ro.alexk.energyutilityplatformbackend.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.mappers.UserMapper;
import ro.alexk.energyutilityplatformbackend.services.UserService;

@Validated
@RestController
@RequestMapping(UserController.PATH)
public class UserController extends BaseController<User, UserCreateDto, UserDto, UserUpdateDto> {

    public static final String PATH = "/api/users";

    private final UserService userService;

    private final UserMapper mapper;


    public UserController(UserService service, UserMapper mapper) {
        super(service, mapper);
        this.userService = service;
        this.mapper = mapper;
    }
}
