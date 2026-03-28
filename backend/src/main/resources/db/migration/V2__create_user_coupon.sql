CREATE TABLE user_coupon
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 쿠폰 고유 번호',
    user_id    BIGINT NOT NULL COMMENT '사용자 고유 번호',
    coupon_id  BIGINT NOT NULL COMMENT '쿠폰 고유 번호',
    used_at    DATETIME(6) NULL COMMENT '사용 일시',
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '생성 일시',
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '수정 일시',

    CONSTRAINT uk_user_id_coupon_id UNIQUE (user_id, coupon_id),
    CONSTRAINT fk_user_coupon_coupon_id FOREIGN KEY (coupon_id) REFERENCES coupon (id)
) ENGINE=InnoDB;