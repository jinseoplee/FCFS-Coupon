package io.github.jinseoplee.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "coupon_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_coupon_coupon_id")
    )
    private Coupon coupon;

    private UserCoupon(Long userId, Coupon coupon) {
        this.userId = userId;
        this.coupon = coupon;
    }

    public static UserCoupon create(Long userId, Coupon coupon) {
        return new UserCoupon(userId, coupon);
    }
}