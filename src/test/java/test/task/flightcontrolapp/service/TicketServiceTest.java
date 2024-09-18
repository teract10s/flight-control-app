package test.task.flightcontrolapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.task.flightcontrolapp.dtos.CouponDiscountRequestDto;
import test.task.flightcontrolapp.exception.EntityNotFoundException;
import test.task.flightcontrolapp.model.Ticket;
import test.task.flightcontrolapp.repository.CouponRepository;
import test.task.flightcontrolapp.repository.TicketRepository;

class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private CouponRepository couponRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isAvailable_ShouldReturnTrue_WhenTicketHasNoPassengerId() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setPassengerId(null);

        when(ticketRepository.findById(ticketId)).thenReturn(java.util.Optional.of(ticket));

        boolean result = ticketService.isAvailable(ticketId);

        assertTrue(result, "Ticket should be available when passengerId is null");
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void isAvailable_ShouldReturnFalse_WhenTicketHasPassengerId() {
        Long ticketId = 2L;
        Ticket ticket = new Ticket();
        ticket.setPassengerId(1L);

        when(ticketRepository.findById(ticketId)).thenReturn(java.util.Optional.of(ticket));
        boolean result = ticketService.isAvailable(ticketId);

        assertFalse(result, "Ticket should not be available when passengerId is not null");
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void isAvailable_ShouldThrowException_WhenTicketNotFound() {
        Long ticketId = 3L;

        when(ticketRepository.findById(ticketId)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            ticketService.isAvailable(ticketId);
        });
        assertEquals("Can't found ticket by id: " + ticketId, exception.getMessage());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void calculateDiscount_ShouldReturnNewPrice_WhenCouponIsValid() {
        CouponDiscountRequestDto requestDto = new CouponDiscountRequestDto(123L, 100.0);
        when(couponRepository.contains(123L)).thenReturn(true);

        ResponseEntity<String> response = ticketService.calculateDiscount(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Congratulation! Your new price is"));
    }

    @Test
    public void calculateDiscount_ShouldThrowException_WhenCouponIsInvalid() {
        CouponDiscountRequestDto requestDto = new CouponDiscountRequestDto(999L, 100.0);
        when(couponRepository.contains(999L)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            ticketService.calculateDiscount(requestDto);
        });
        assertEquals("Can't found coupon with such code.", exception.getMessage());
    }
}
