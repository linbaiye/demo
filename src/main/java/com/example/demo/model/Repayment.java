package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@Builder
public class Repayment {
    private Long id;

    private final String applicationNo;

    private final BigDecimal amount;

    private final LocalDateTime paidTime;

    public Repayment(Long id,
                     String applicationNo,
                     BigDecimal amount,
                     LocalDateTime paidTime) {
        // invariant check.
        this.id = id;
        this.applicationNo = applicationNo;
        this.amount = amount.setScale(4, RoundingMode.HALF_UP);
        this.paidTime = paidTime;
    }
}
