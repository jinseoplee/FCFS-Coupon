package io.github.jinseoplee.service;

import io.github.jinseoplee.entity.Coupon;
import io.github.jinseoplee.entity.UserCoupon;
import io.github.jinseoplee.exception.BusinessException;
import io.github.jinseoplee.exception.ErrorCode;
import io.github.jinseoplee.repository.CouponRepository;
import io.github.jinseoplee.repository.UserCouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class CouponIssueServiceTest {

    @InjectMocks
    private CouponIssueService couponIssueService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private UserCouponRepository userCouponRepository;

    @Test
    void issue_success() {
        // given
        Long couponId = 1L;
        Long userId = 1L;
        Coupon coupon = Coupon.create("테스트 쿠폰", 100);

        given(couponRepository.findById(couponId))
                .willReturn(Optional.of(coupon));
        given(userCouponRepository.existsByUserIdAndCouponId(userId, couponId))
                .willReturn(false);

        // when
        couponIssueService.issue(userId, couponId);

        // then
        assertThat(coupon.getIssuedQuantity()).isEqualTo(1);
        then(userCouponRepository).should().save(any(UserCoupon.class));
    }

    @Test
    void issue_fail_not_found() {
        // given
        Long nonexistentCouponId = 9999999L;
        Long userId = 1L;

        given(couponRepository.findById(nonexistentCouponId))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> couponIssueService.issue(userId, nonexistentCouponId))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.COUPON_NOT_FOUND);
        then(userCouponRepository).shouldHaveNoInteractions();
    }

    @Test
    void issue_fail_already_issued() {
        // given
        Long alreadyIssuedCouponId = 1L;
        Long userId = 1L;
        Coupon coupon = Coupon.create("테스트 쿠폰", 100);

        given(couponRepository.findById(alreadyIssuedCouponId))
                .willReturn(Optional.of(coupon));
        given(userCouponRepository.existsByUserIdAndCouponId(userId, alreadyIssuedCouponId))
                .willReturn(true);

        // when & then
        assertThatThrownBy(() -> couponIssueService.issue(userId, alreadyIssuedCouponId))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.COUPON_ALREADY_ISSUED);
        then(userCouponRepository).should(never()).save(any(UserCoupon.class));
    }

    @Test
    void issue_fail_exhausted() {
        // given
        Long exhaustedCouponId = 1L;
        Long userId = 1L;
        Coupon coupon = Coupon.create("테스트 쿠폰", 0);

        given(couponRepository.findById(exhaustedCouponId))
                .willReturn(Optional.of(coupon));
        given(userCouponRepository.existsByUserIdAndCouponId(userId, exhaustedCouponId))
                .willReturn(false);

        // when & then
        assertThatThrownBy(() -> couponIssueService.issue(userId, exhaustedCouponId))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.COUPON_EXHAUSTED);
        then(userCouponRepository).should(never()).save(any(UserCoupon.class));
    }

}