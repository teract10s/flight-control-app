package test.task.flightcontrolapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import test.task.flightcontrolapp.dtos.CheckInDto;
import test.task.flightcontrolapp.exception.EntityNotFoundException;
import test.task.flightcontrolapp.model.Baggage;
import test.task.flightcontrolapp.model.Destination;
import test.task.flightcontrolapp.model.Flight;
import test.task.flightcontrolapp.model.Ticket;
import test.task.flightcontrolapp.repository.BaggageRepository;
import test.task.flightcontrolapp.repository.DestinationRepository;
import test.task.flightcontrolapp.repository.FlightRepository;
import test.task.flightcontrolapp.repository.TicketRepository;

public class BaggageServiceTest {

    @InjectMocks
    private BaggageService baggageService;

    @Mock
    private BaggageRepository baggageRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkIn_ShouldReturnTrue_WhenBaggageIsAssignedToDestination() {
        Long baggageId = 1L;
        Long destinationId = 2L;
        Long flightId = 3L;
        Long ticketId = 4L;
        Long passengerId = 5L;

        Baggage baggage = new Baggage();
        baggage.setPassengerId(passengerId);

        Destination destination = new Destination();
        destination.setFlightsId(Collections.singletonList(flightId));

        Flight flight = new Flight();
        flight.setTicketsId(Collections.singletonList(ticketId));

        Ticket ticket = new Ticket();
        ticket.setPassengerId(passengerId);

        when(baggageRepository.findById(baggageId)).thenReturn(Optional.of(baggage));
        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(destination));
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        CheckInDto checkInDto = new CheckInDto(baggageId, destinationId);

        boolean result = baggageService.checkIn(checkInDto);

        assertTrue(result, "Baggage should be assigned to the destination");
        verify(baggageRepository, times(1)).findById(baggageId);
        verify(destinationRepository, times(1)).findById(destinationId);
        verify(flightRepository, times(1)).findById(flightId);
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void checkIn_ShouldReturnFalse_WhenBaggageIsNotAssignedToDestination() {
        Long baggageId = 6L;
        Long destinationId = 7L;
        Long flightId = 8L;
        Long ticketId = 9L;
        Long passengerId = 10L;

        Baggage baggage = new Baggage();
        baggage.setPassengerId(passengerId);

        Destination destination = new Destination();
        destination.setFlightsId(Collections.singletonList(flightId));

        Flight flight = new Flight();
        flight.setTicketsId(Collections.singletonList(ticketId));

        Ticket ticket = new Ticket();
        ticket.setPassengerId(11L);

        when(baggageRepository.findById(baggageId)).thenReturn(Optional.of(baggage));
        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(destination));
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        CheckInDto checkInDto = new CheckInDto(baggageId, destinationId);

        boolean result = baggageService.checkIn(checkInDto);

        assertFalse(result, "Baggage should not be assigned to the destination");
        verify(baggageRepository, times(1)).findById(baggageId);
        verify(destinationRepository, times(1)).findById(destinationId);
        verify(flightRepository, times(1)).findById(flightId);
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void checkIn_ShouldThrowException_WhenBaggageNotFound() {
        Long baggageId = 12L;
        Long destinationId = 13L;

        when(baggageRepository.findById(baggageId)).thenReturn(Optional.empty());

        CheckInDto checkInDto = new CheckInDto(baggageId, destinationId);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            baggageService.checkIn(checkInDto);
        });
        assertEquals("Can't found baggage by id: " + baggageId, exception.getMessage());
        verify(baggageRepository, times(1)).findById(baggageId);
        verify(destinationRepository, never()).findById(anyLong());
        verify(flightRepository, never()).findById(anyLong());
        verify(ticketRepository, never()).findById(anyLong());
    }

    @Test
    public void checkIn_ShouldThrowException_WhenDestinationNotFound() {
        Long baggageId = 14L;
        Long destinationId = 15L;

        Baggage baggage = new Baggage();
        baggage.setPassengerId(16L);

        when(baggageRepository.findById(baggageId)).thenReturn(Optional.of(baggage));
        when(destinationRepository.findById(destinationId)).thenReturn(Optional.empty());

        CheckInDto checkInDto = new CheckInDto(baggageId, destinationId);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            baggageService.checkIn(checkInDto);
        });
        assertEquals("Can't found destination by id: " + destinationId, exception.getMessage());
        verify(baggageRepository, times(1)).findById(baggageId);
        verify(destinationRepository, times(1)).findById(destinationId);
        verify(flightRepository, never()).findById(anyLong());
        verify(ticketRepository, never()).findById(anyLong());
    }
}
