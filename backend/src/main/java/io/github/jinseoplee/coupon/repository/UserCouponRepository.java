package io.github.jinseoplee.coupon.repository;

import io.github.jinseoplee.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
