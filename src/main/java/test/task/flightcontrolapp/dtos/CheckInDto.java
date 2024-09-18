package test.task.flightcontrolapp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CheckInDto(
        @Min(0) Long baggageId,
        @Min(0) Long destinationId
) {
}
