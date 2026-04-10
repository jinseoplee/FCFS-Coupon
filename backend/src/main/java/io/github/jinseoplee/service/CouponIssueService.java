package io.github.jinseoplee.service;

import io.github.jinseoplee.entity.Coupon;
import io.github.jinseoplee.entity.UserCoupon;
import io.github.jinseoplee.exception.BusinessException;
import io.github.jinseoplee.exception.ErrorCode;
import io.github.jinseoplee.repository.CouponRepository;
import io.github.jinseoplee.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void issue(Long couponId, Long userId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        if (userCouponRepository.existsByUserIdAndCouponId(userId, couponId)) {
            throw new BusinessException(ErrorCode.COUPON_ALREADY_ISSUED);
        }

        coupon.issue();

        UserCoupon userCoupon = UserCoupon.create(userId, coupon);
        userCouponRepository.save(userCoupon);
    }
}
