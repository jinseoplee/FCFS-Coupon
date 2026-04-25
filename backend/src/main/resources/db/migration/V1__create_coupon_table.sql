CREATE TABLE coupon
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    title                  VARCHAR(255) NOT NULL,
    status                 VARCHAR(30)  NOT NULL DEFAULT 'READY',

    total_quantity         INT          NOT NULL,
    issued_quantity        INT          NOT NULL DEFAULT 0,

    issue_start_at         DATETIME(6) NOT NULL,
    issue_end_at           DATETIME(6) NOT NULL,

    period_type            VARCHAR(30)  NOT NULL,
    use_end_at             DATETIME(6) NULL,
    valid_days_after_issue INT NULL,

    created_at             DATETIME(6) NOT NULL,
    updated_at             DATETIME(6) NOT NULL
)ENGINE=InnoDB;