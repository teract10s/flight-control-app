package test.task.flightcontrolapp.service;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.task.flightcontrolapp.dtos.CouponDiscountRequestDto;
import test.task.flightcontrolapp.exception.EntityNotFoundException;
import test.task.flightcontrolapp.model.Ticket;
import test.task.flightcontrolapp.repository.CouponRepository;
import test.task.flightcontrolapp.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {

    private static final double[] DISCOUNTS = {0.1, 0.5, 0.6};
    private static final Random RANDOM = new Random();
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    private final TicketRepository ticketRepository;
    private final CouponRepository couponRepository;

    public boolean isAvailable(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found ticket by id: " + id));
        logger.info("Checking ticket with id=" + id + " for availability");
        return ticket.getPassengerId() == null;
    }

    public ResponseEntity<String> calculateDiscount(CouponDiscountRequestDto discountRequestDto) {
        logger.info(String.format("Calculation discount for coupon with code=%d and price=%.2f",
                discountRequestDto.couponCode(), discountRequestDto.ticketPrice()));
        if (couponRepository.contains(discountRequestDto.couponCode())) {
            double newPrice =
                    discountRequestDto.ticketPrice() - (discountRequestDto.ticketPrice() * getRandomDiscount());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(String.format("Congratulation! Your new price is %.2f", newPrice));
        }
        throw new EntityNotFoundException("Can't found coupon with such code.");
    }

    public static double getRandomDiscount() {
        return DISCOUNTS[RANDOM.nextInt(DISCOUNTS.length)];
    }
}
