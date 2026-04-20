CREATE TABLE coupon
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL COMMENT '쿠폰명',
    status          VARCHAR(30)  NOT NULL DEFAULT 'READY' COMMENT '상태(READY, AVAILABLE, EXHAUSTED, ENDED)',

    total_quantity  INT          NOT NULL COMMENT '발급 가능 수량',
    issued_quantity INT          NOT NULL DEFAULT 0 COMMENT '현재 발급 수량',

    started_at      DATETIME(6) NOT NULL COMMENT '발급 시작 일시',
    ended_at        DATETIME(6) NOT NULL COMMENT '발급 종료 일시',

    expiry_type     VARCHAR(20)  NOT NULL COMMENT '만료 타입(FIXED, RELATIVE)',
    expires_at      DATETIME(6) NULL COMMENT '만료 일시(FIXED)',
    relative_days   INT NULL COMMENT '상대 기간(RELATIVE)',

    created_at      DATETIME(6) NOT NULL COMMENT '생성 일시',
    updated_at      DATETIME(6) NOT NULL COMMENT '수정 일시'
) ENGINE=InnoDB;