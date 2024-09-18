package test.task.flightcontrolapp.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test.task.flightcontrolapp.dtos.CheckInDto;
import test.task.flightcontrolapp.exception.EntityNotFoundException;
import test.task.flightcontrolapp.model.Baggage;
import test.task.flightcontrolapp.model.Destination;
import test.task.flightcontrolapp.repository.BaggageRepository;
import test.task.flightcontrolapp.repository.DestinationRepository;
import test.task.flightcontrolapp.repository.FlightRepository;
import test.task.flightcontrolapp.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class BaggageService {
    private static final Logger logger = LoggerFactory.getLogger(BaggageService.class);
    private final BaggageRepository baggageRepository;
    private final DestinationRepository destinationRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    public boolean checkIn(CheckInDto checkInDto) {
        Baggage baggage = baggageRepository.findById(checkInDto.baggageId())
                .orElseThrow(() -> new EntityNotFoundException("Can't found baggage by id: " + checkInDto.baggageId()));
        Destination destination = destinationRepository.findById(checkInDto.destinationId())
                .orElseThrow(() -> new EntityNotFoundException("Can't found destination by id: " + checkInDto.destinationId()));

        logger.info(String.format("Checking that baggage with id=%d is relate to destination with id=%d",
                checkInDto.baggageId(), checkInDto.destinationId()));
        return destination.getFlightsId().stream()
                .map(fi -> flightRepository.findById(fi).get())
                .flatMap(f -> f.getTicketsId().stream()
                        .map(ti -> ticketRepository.findById(ti).get()))
                .anyMatch(t -> Objects.equals(t.getPassengerId(), baggage.getPassengerId()));
    }
}
