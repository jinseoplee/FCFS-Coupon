package io.github.jinseoplee.repository;

import io.github.jinseoplee.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
