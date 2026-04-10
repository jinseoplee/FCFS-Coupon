package io.github.jinseoplee.entity;

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
    private int totalQuantity;

    @Column(nullable = false)
    private int issuedQuantity;

    private Coupon(String title, int totalQuantity) {
        this.title = title;
        this.totalQuantity = totalQuantity;
    }

    public static Coupon create(String title, int totalQuantity) {
        return new Coupon(title, totalQuantity);
    }
}
