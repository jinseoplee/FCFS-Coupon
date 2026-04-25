CREATE TABLE user_coupon
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT      NOT NULL,
    coupon_id  BIGINT      NOT NULL,
    status     VARCHAR(30) NOT NULL DEFAULT 'ISSUED',

    use_end_at DATETIME(6) NOT NULL,
    used_at    DATETIME(6) NULL,

    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    CONSTRAINT uk_user_coupon_user_id_coupon_id UNIQUE KEY (user_id, coupon_id),
    CONSTRAINT fk_user_coupon_coupon_id FOREIGN KEY (coupon_id) REFERENCES coupon (id)
)ENGINE=InnoDB;