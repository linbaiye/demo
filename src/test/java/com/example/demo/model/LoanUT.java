package com.example.demo.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
    public void testGetTipMessage() {

    }
}
