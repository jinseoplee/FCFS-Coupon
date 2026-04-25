package io.github.jinseoplee.coupon.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCouponStatus {

    ISSUED("발급됨"),
    USED("사용 완료"),
    EXPIRED("기간 만료");

    private final String description;
}
