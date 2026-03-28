package io.github.jinseoplee.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "CP001", "존재하지 않는 쿠폰입니다."),
    COUPON_ALREADY_ISSUED(HttpStatus.CONFLICT, "CP002", "이미 발급된 쿠폰입니다."),
    COUPON_EXHAUSTED(HttpStatus.BAD_REQUEST, "CP003", "쿠폰이 모두 소진되었습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "서버 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
