package ro.alexk.energyutilityplatformbackend.dtos.measurement;

public record MeasurementMQDto(
        Long timestamp,
        String device_id,
        Double measurement_value
) {
}
