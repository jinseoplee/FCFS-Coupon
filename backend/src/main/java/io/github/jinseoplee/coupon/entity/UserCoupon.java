package io.github.jinseoplee.coupon.entity;

import io.github.jinseoplee.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user_coupon",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_coupon_user_id_coupon_id",
                        columnNames = {"user_id", "coupon_id"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "coupon_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_coupon_coupon_id")
    )
    private Coupon coupon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserCouponStatus status = UserCouponStatus.ISSUED;

    private LocalDateTime usedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private UserCoupon(Long userId, Coupon coupon, LocalDateTime expiresAt) {
        this.userId = userId;
        this.coupon = coupon;
        this.expiresAt = expiresAt;
    }

    public static UserCoupon create(Long userId, Coupon coupon, LocalDateTime expiresAt) {
        return new UserCoupon(userId, coupon, expiresAt);
    }
}
