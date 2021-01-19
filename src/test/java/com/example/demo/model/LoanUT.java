package com.example.demo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanUT {

    @Test
    public void calculateOutstandingBalance_should_sumPrincipleAndInterest() {
        Loan loan = Loan.builder()
                .loanTerm(1)
                .principle(BigDecimal.valueOf(1000))
                .dailyInterestRate(new BigDecimal("0.0001"))
                .interest(BigDecimal.ZERO)
                .build();
        Assert.assertEquals(0, BigDecimal.valueOf(1000).compareTo(loan.calculateOutstandingBalance()));
    }

    @Test
    public void repay_should_repayInterestFirst() {
        Repayment repayment = new Repayment(null, "1", new BigDecimal(10), LocalDateTime.now());
        Loan loan = Loan.builder().principle(new BigDecimal(100))
                .interest(new BigDecimal(9)).build();
        Assert.assertNull(loan.repay(repayment));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(loan.getInterest()));

        //more tests.
    }
}
