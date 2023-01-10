package ro.alexk.energyutilityplatformbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.alexk.energyutilityplatformbackend.annotations.Admin;
import ro.alexk.energyutilityplatformbackend.annotations.CheckUUID;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceDto;
import ro.alexk.energyutilityplatformbackend.dtos.device.DeviceUpdateDto;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementCreateDto;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementDto;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.mappers.DeviceMapper;
import ro.alexk.energyutilityplatformbackend.mappers.MeasurementMapper;
import ro.alexk.energyutilityplatformbackend.mappers.context.DeviceUpdateContext;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;

import javax.validation.Valid;
import java.util.Set;

@Admin
@Validated
@RestController
@RequestMapping(DeviceController.PATH)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeviceController
        extends BaseController<Device, DeviceCreateDto, DeviceDto, DeviceUpdateDto, DeviceUpdateContext> {

    public static final String PATH = "/api/devices";

    private final DeviceService deviceService;

    private final DeviceMapper mapper;

    private final MeasurementMapper measurementMapper;

    public DeviceController(DeviceService service, DeviceMapper mapper, MeasurementMapper measurementMapper) {
        super(service, mapper, DeviceUpdateContext::new);
        this.deviceService = service;
        this.mapper = mapper;
        this.measurementMapper = measurementMapper;
    }

    @GetMapping("/available")
    public ResponseEntity<Set<DeviceDto>> getAvailable() {
        var devices = deviceService.findAvailable();
        return ResponseEntity.ok(mapper.map(devices));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    @GetMapping("/{deviceId}/measurements")
    public ResponseEntity<Set<MeasurementDto>> getMeasurements(@PathVariable @CheckUUID String deviceId) {
        var measurements = deviceService.getMeasurements(deviceId);
        return ResponseEntity.ok(measurementMapper.map(measurements));
    }

    @PostMapping("/{deviceId}/measurements")
    public ResponseEntity<Void> addMeasurement(
            @PathVariable @CheckUUID String deviceId, @RequestBody @Valid MeasurementCreateDto measurementCreateDto
    ) {
        deviceService.addMeasurement(deviceId, measurementMapper.map(measurementCreateDto));
        return ResponseEntity.ok().build();
    }
}
