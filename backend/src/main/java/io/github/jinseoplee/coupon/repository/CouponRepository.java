package io.github.jinseoplee.coupon.repository;

import io.github.jinseoplee.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
