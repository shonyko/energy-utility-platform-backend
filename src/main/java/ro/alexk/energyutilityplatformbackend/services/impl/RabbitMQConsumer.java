package ro.alexk.energyutilityplatformbackend.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ro.alexk.energyutilityplatformbackend.dtos.measurement.MeasurementMQDto;
import ro.alexk.energyutilityplatformbackend.mappers.MeasurementMapper;
import ro.alexk.energyutilityplatformbackend.services.DeviceService;

@Slf4j
@Service
@RequiredArgsConstructor
@RabbitListener(queues = "${mq.queue}")
public class RabbitMQConsumer {

    private final DeviceService deviceService;

    private final MeasurementMapper measurementMapper;

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

        var measurement = measurementMapper.map(measurementDto);
        deviceService.addMeasurement(measurementDto.device_id(), measurement);
        log.info("Saved: " + measurement);
    }
}
