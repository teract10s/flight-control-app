package test.task.flightcontrolapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.flightcontrolapp.dtos.CheckInDto;
import test.task.flightcontrolapp.service.BaggageService;

@Tag(name = "Baggage management", description = "Endpoints for managing baggage")
@RestController
@RequiredArgsConstructor
@RequestMapping("/baggage")
public class BaggageController {
    private final BaggageService baggageService;

    @PostMapping
    @Operation(summary = "Baggage check-in")
    public boolean checkIn(@RequestBody @Valid CheckInDto checkInDto) {
        return baggageService.checkIn(checkInDto);
    }
}
