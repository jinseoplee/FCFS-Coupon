package io.github.jinseoplee.coupon.entity;

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
    private LocalDateTime issueStartAt;

    @Column(nullable = false)
    private LocalDateTime issueEndAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CouponPeriodType periodType;

    private LocalDateTime useEndAt;
    private Integer validDaysAfterIssue;

    private Coupon(String title, int totalQuantity, LocalDateTime issueStartAt, LocalDateTime issueEndAt,
                   CouponPeriodType periodType, LocalDateTime useEndAt, Integer validDaysAfterIssue) {
        this.title = title;
        this.totalQuantity = totalQuantity;
        this.issueStartAt = issueStartAt;
        this.issueEndAt = issueEndAt;
        this.periodType = periodType;
        this.useEndAt = useEndAt;
        this.validDaysAfterIssue = validDaysAfterIssue;
    }

    public static Coupon create(String title, int totalQuantity, LocalDateTime issueStartAt, LocalDateTime issueEndAt,
                                CouponPeriodType periodType, LocalDateTime useEndAt, Integer validDaysAfterIssue) {
        return new Coupon(title, totalQuantity, issueStartAt, issueEndAt, periodType, useEndAt, validDaysAfterIssue);
    }
}
