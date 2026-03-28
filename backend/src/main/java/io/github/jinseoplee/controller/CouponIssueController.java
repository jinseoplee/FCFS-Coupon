package io.github.jinseoplee.controller;

import io.github.jinseoplee.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponIssueController {

    private final CouponIssueService couponIssueService;

    @PostMapping("/{couponId}/issue")
    public ResponseEntity<Void> issue(@PathVariable(name = "couponId") Long couponId,
                                      @RequestHeader("X-User-Id") Long userId) {
        couponIssueService.issue(userId, couponId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
