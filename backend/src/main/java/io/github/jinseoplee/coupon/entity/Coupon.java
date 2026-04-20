package io.github.jinseoplee.coupon.entity;

import io.github.jinseoplee.common.entity.BaseTimeEntity;
import io.github.jinseoplee.coupon.enums.CouponStatus;
import io.github.jinseoplee.coupon.enums.ExpiryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CouponStatus status = CouponStatus.READY;

    private int totalQuantity;
    private int issuedQuantity = 0;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime endedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExpiryType expiryType;

    private Integer relativeDays;
    private LocalDateTime expiresAt;

    private Coupon(String title, int totalQuantity, LocalDateTime startedAt, LocalDateTime endedAt,
                   ExpiryType expiryType, Integer relativeDays, LocalDateTime expiresAt) {
        this.title = title;
        this.totalQuantity = totalQuantity;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiryType = expiryType;
        this.relativeDays = relativeDays;
        this.expiresAt = expiresAt;

        validatePolicy();
    }

    public static Coupon create(String title, int totalQuantity, LocalDateTime startedAt, LocalDateTime endedAt,
                                ExpiryType expiryType, Integer relativeDays, LocalDateTime expiresAt) {
        return new Coupon(title, totalQuantity, startedAt, endedAt, expiryType, relativeDays, expiresAt);
    }

    private void validatePolicy() {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("startedAt이 endedAt보다 늦을 수 없습니다.");
        }

        if (expiryType == ExpiryType.FIXED && expiresAt == null) {
            throw new IllegalArgumentException("FIXED 타입은 expiresAt이 필수입니다.");
        }

        if (expiryType == ExpiryType.RELATIVE && relativeDays == null) {
            throw new IllegalArgumentException("RELATIVE 타입은 relativeDays가 필수입니다.");
        }
    }
}
