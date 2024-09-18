package test.task.flightcontrolapp.dtos;

import jakarta.validation.constraints.Min;

public record CouponDiscountRequestDto(
        Long couponCode,
        @Min(0) Double ticketPrice
) {
}
