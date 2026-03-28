CREATE TABLE coupon
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '쿠폰 고유 번호',
    title           VARCHAR(255) NOT NULL COMMENT '쿠폰명',
    total_quantity  INT          NOT NULL COMMENT '총 발급 가능 수량',
    issued_quantity INT          NOT NULL DEFAULT 0 COMMENT '현재까지 발급된 수량',
    created_at      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '생성 일시',
    updated_at      DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '수정 일시'
) ENGINE=InnoDB;