package io.github.jinseoplee.coupon.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {

    READY("발급 대기"),
    DISTRIBUTING("발급 중"),
    PAUSED("발급 중지"),
    EXHAUSTED("쿠폰 소진"),
    EXPIRED("기간 만료");

    private final String description;
}
