package test.task.flightcontrolapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.flightcontrolapp.dtos.CouponDiscountRequestDto;
import test.task.flightcontrolapp.service.TicketService;

@Tag(name = "Tickets management", description = "Endpoints for managing tickets")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    @Operation(summary = "Indicates whether the ticket is available")
    public boolean isAvailable(@PathVariable Long id) {
        return ticketService.isAvailable(id);
    }

    @PostMapping("/coupon_discount")
    @Operation(summary = "Calculate discount depends of coupon code")
    public ResponseEntity<String> calculateDiscount(@RequestBody @Valid CouponDiscountRequestDto couponDiscountRequest) {
        return ticketService.calculateDiscount(couponDiscountRequest);
    }
}
