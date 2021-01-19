package com.example.demo.model;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author tao.lin
 * @date 2021/1/19
 */
@Getter
public class OverRepayment {

    private Long loanId;

    private BigDecimal amount;

    public OverRepayment(Long loanId,
                         BigDecimal amount) {
        this.loanId = loanId;
        this.amount = amount;
    }
}
