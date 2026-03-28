package io.github.jinseoplee.repository;

import io.github.jinseoplee.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
