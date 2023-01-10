package ro.alexk.energyutilityplatformbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.alexk.energyutilityplatformbackend.annotations.Admin;
import ro.alexk.energyutilityplatformbackend.annotations.CheckUUID;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserDto;
import ro.alexk.energyutilityplatformbackend.dtos.user.UserUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.User;
import ro.alexk.energyutilityplatformbackend.enums.Role;
import ro.alexk.energyutilityplatformbackend.mappers.DeviceMapper;
import ro.alexk.energyutilityplatformbackend.mappers.UserMapper;
import ro.alexk.energyutilityplatformbackend.mappers.context.UserUpdateContext;
import ro.alexk.energyutilityplatformbackend.services.UserService;

import javax.validation.Valid;
import java.util.Set;

@Validated
@RestController
@RequestMapping(UserController.PATH)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController extends BaseController<User, UserCreateDto, UserDto, UserUpdateDto, UserUpdateContext> {

    public static final String PATH = "/api/users";

    private final UserService userService;

    private final UserMapper userMapper;
    private final DeviceMapper deviceMapper;

    public UserController(UserService service, UserMapper mapper, DeviceMapper deviceMapper) {
        super(service, mapper, UserUpdateContext::new);
        this.userService = service;
        this.userMapper = mapper;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public ResponseEntity<UserDto> get(String id) {
        return super.get(id);
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity<Set<UserDto>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(
            userMapper.map(userService.findByRole(role))
        );
    }

    @GetMapping("/{userId}/devices")
    public ResponseEntity<Set<DeviceDto>> getDevices(@PathVariable @CheckUUID String userId) {
        return ResponseEntity.ok(
                deviceMapper.map(userService.getDevices(userId))
        );
    }

    @Admin
    @PostMapping("/{userId}/devices/{deviceId}")
    public ResponseEntity<Void> addDevice(
            @PathVariable @CheckUUID String userId, @PathVariable @CheckUUID String deviceId
    ) {
        userService.addDevice(userId, deviceId);
        return ResponseEntity.ok().build();
    }

    @Admin
    @DeleteMapping("/{userId}/devices/{deviceId}")
    public ResponseEntity<Void> removeDevice(
            @PathVariable @CheckUUID String userId, @PathVariable @CheckUUID String deviceId
    ) {
        userService.removeDevice(userId, deviceId);
        return ResponseEntity.noContent().build();
    }

    @Admin
    @Override
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateDto dto) {
        System.out.println(dto);
        return super.create(dto);
    }

    @Admin
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable @CheckUUID String id, @RequestBody @Valid UserUpdateDto dto) {
        return super.update(id, dto);
    }

    @Admin
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @CheckUUID String id) {
        return super.delete(id);
    }
}
