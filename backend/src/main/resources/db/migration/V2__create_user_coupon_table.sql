CREATE TABLE user_coupon
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT      NOT NULL COMMENT '사용자 ID',
    coupon_id  BIGINT      NOT NULL COMMENT '쿠폰 ID',
    status     VARCHAR(30) NOT NULL DEFAULT 'ISSUED' COMMENT '상태(ISSUED, USED, EXPIRED)',

    used_at    DATETIME(6) NULL COMMENT '사용 일시',
    expires_at DATETIME(6) NOT NULL COMMENT '만료 일시',

    created_at DATETIME(6) NOT NULL COMMENT '발급 일시',
    updated_at DATETIME(6) NOT NULL COMMENT '수정 일시',

    CONSTRAINT uk_user_coupon_user_id_coupon_id UNIQUE KEY (user_id, coupon_id),
    CONSTRAINT fk_user_coupon_coupon_id FOREIGN KEY (coupon_id) REFERENCES coupon (id)
) ENGINE=InnoDB;