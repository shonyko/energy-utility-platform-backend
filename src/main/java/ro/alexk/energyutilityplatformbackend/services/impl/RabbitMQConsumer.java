package ro.alexk.energyutilityplatformbackend.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementMQDto;
import ro.alexk.energyutilityplatformbackend.entities.Device;
import ro.alexk.energyutilityplatformbackend.mappers.MeasurementMapper;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@RabbitListener(queues = "${mq.queue}")
public class RabbitMQConsumer {

    private final DeviceService deviceService;

    private final MeasurementMapper measurementMapper;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitHandler
    public void receive(String message) {
        var jsonConverter = new ObjectMapper();


        MeasurementMQDto measurementDto;
        try {
            measurementDto = jsonConverter.readValue(message, MeasurementMQDto.class);
        } catch (JsonProcessingException e) {
            log.warn("Could not parse MQ message JSON.");
            return;
        }

        Device device;
        try {
            device = deviceService.findById(measurementDto.device_id());
        } catch (Exception e) {
            log.error("Device not found: " + measurementDto.device_id());
            return;
        }

        var measurement = measurementMapper.map(measurementDto);

        if (measurement.getEnergyConsumption() > device.getMaxHourlyConsumption()) {
            measurement.setEnergyConsumption(device.getMaxHourlyConsumption());
            log.info("Max hourly consumption exceeded for device: " + device.getId());
            sendWarning(device.getUser().getId(), "Max hourly consumption exceeded for device: " + device.getId());
        }

        deviceService.addMeasurement(measurementDto.device_id(), measurement);
        log.info("Saved: " + measurement);
    }

    public void sendWarning(UUID userId, String message) {
        simpMessagingTemplate.convertAndSend("/topic/" + userId, message);
        log.info("Warning sent to user: " + userId);
    }
}
