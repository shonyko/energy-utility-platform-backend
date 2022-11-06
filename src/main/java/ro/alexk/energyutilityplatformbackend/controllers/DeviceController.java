package ro.alexk.energyutilityplatformbackend.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceUpdateDto;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.mappers.DeviceMapper;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;

@Validated
@RestController
@RequestMapping(DeviceController.PATH)
public class DeviceController extends BaseController<Device, DeviceCreateDto, DeviceDto, DeviceUpdateDto> {

    public static final String PATH = "/api/devices";

    private final DeviceService deviceService;

    private final DeviceMapper mapper;

    public DeviceController(DeviceService service, DeviceMapper mapper) {
        super(service, mapper);
        this.deviceService = service;
        this.mapper = mapper;
    }
}
