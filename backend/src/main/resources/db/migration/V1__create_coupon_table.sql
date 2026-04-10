CREATE TABLE coupon
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    total_quantity  INT          NOT NULL,
    issued_quantity INT          NOT NULL DEFAULT 0,
    created_at      TIMESTAMP(6) NOT NULL,
    updated_at      TIMESTAMP(6) NOT NULL
) ENGINE=InnoDB;