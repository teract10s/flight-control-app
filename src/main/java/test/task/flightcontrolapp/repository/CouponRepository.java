package test.task.flightcontrolapp.repository;

import java.util.Set;

public class CouponRepository {
    private Set<Long> coupons;

    public CouponRepository(Set<Long> coupons) {
        this.coupons = coupons;
    }

    public boolean contains(Long couponCode) {
        return coupons.contains(couponCode);
    }
}
