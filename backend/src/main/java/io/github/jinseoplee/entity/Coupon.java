package io.github.jinseoplee.entity;

import io.github.jinseoplee.exception.BusinessException;
import io.github.jinseoplee.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private Integer issuedQuantity;

    private Coupon(String title, Integer totalQuantity) {
        this.title = title;
        this.totalQuantity = totalQuantity;
        this.issuedQuantity = 0;
    }

    public static Coupon create(String title, Integer totalQuantity) {
        return new Coupon(title, totalQuantity);
    }

    public void issue() {
        if (this.issuedQuantity >= this.totalQuantity) {
            throw new BusinessException(ErrorCode.COUPON_EXHAUSTED);
        }
        this.issuedQuantity++;
    }

}
