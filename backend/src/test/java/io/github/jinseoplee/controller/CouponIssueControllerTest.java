package io.github.jinseoplee.controller;

import io.github.jinseoplee.exception.BusinessException;
import io.github.jinseoplee.exception.ErrorCode;
import io.github.jinseoplee.service.CouponIssueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponIssueController.class)
class CouponIssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CouponIssueService couponIssueService;

    @Test
    void issue_success() throws Exception {
        // given
        Long couponId = 1L;
        Long userId = 1L;

        // when & then
        mockMvc.perform(post("/api/coupons/{couponId}/issue", couponId)
                        .header("X-User-Id", userId))
                .andExpect(status().isCreated());
    }

    @Test
    void issue_fail_not_found() throws Exception {
        // given
        Long nonexistentCouponId = 9999999L;
        Long userId = 1L;
        ErrorCode errorCode = ErrorCode.COUPON_NOT_FOUND;

        willThrow(new BusinessException(errorCode))
                .given(couponIssueService).issue(nonexistentCouponId, userId);

        // when & then
        mockMvc.perform(post("/api/coupons/{couponId}/issue", nonexistentCouponId)
                        .header("X-User-Id", userId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(errorCode.getCode()))
                .andExpect(jsonPath("$.message").value(errorCode.getMessage()));
    }

    @Test
    void issue_fail_already_issued() throws Exception {
        // given
        Long alreadyIssuedCouponId = 1L;
        Long userId = 1L;
        ErrorCode errorCode = ErrorCode.COUPON_ALREADY_ISSUED;

        willThrow(new BusinessException(errorCode))
                .given(couponIssueService).issue(alreadyIssuedCouponId, userId);

        // when & then
        mockMvc.perform(post("/api/coupons/{couponId}/issue", alreadyIssuedCouponId)
                        .header("X-User-Id", userId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value(errorCode.getCode()))
                .andExpect(jsonPath("$.message").value(errorCode.getMessage()));
    }

    @Test
    void issue_fail_exhausted() throws Exception {
        // given
        Long exhaustedCouponId = 1L;
        Long userId = 1L;
        ErrorCode errorCode = ErrorCode.COUPON_EXHAUSTED;

        willThrow(new BusinessException(errorCode))
                .given(couponIssueService).issue(exhaustedCouponId, userId);

        // when & then
        mockMvc.perform(post("/api/coupons/{couponId}/issue", exhaustedCouponId)
                        .header("X-User-Id", userId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(errorCode.getCode()))
                .andExpect(jsonPath("$.message").value(errorCode.getMessage()));
    }
}